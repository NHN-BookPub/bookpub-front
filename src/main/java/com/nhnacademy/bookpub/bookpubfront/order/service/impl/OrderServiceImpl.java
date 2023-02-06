package com.nhnacademy.bookpub.bookpubfront.order.service.impl;

import com.nhnacademy.bookpub.bookpubfront.order.adaptor.OrderAdaptor;
import com.nhnacademy.bookpub.bookpubfront.order.dto.request.CreateOrderRequestDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.request.OrderFormRequestDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderListForAdminResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.service.OrderService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 주문 서비스의 구현체입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private static final String PRODUCT_COUPON_DIVIDER = "-";
    private static final String DIVIDER = "\\|";
    private static final String ANONYMOUS = "anonymousUser";
    private final OrderAdaptor orderAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long createOrder(OrderFormRequestDto requestDto, String productInfo) {
        String principal =
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long memberNo = null;

        if (!principal.equals(ANONYMOUS)) {
            memberNo = Long.parseLong(principal);
        }

        List<Long> productNos = new ArrayList<>();
        Map<Long, Integer> productCount = new HashMap<>();
        Map<Long, Long> productAmountMap = new HashMap<>();
        Map<Long, Long> productCoupon = new HashMap<>();
        Map<Long, Long> productSaleAmount = new HashMap<>();

        List<String> productInfoList
                = Arrays.stream(productInfo.split(",")).collect(Collectors.toList());

        for (String productCouponInfo : productInfoList) {
            parsingAndInput(productNos, productCount, productAmountMap,
                    productCoupon, productSaleAmount, productCouponInfo);
        }

        CreateOrderRequestDto createOrderRequestDto = CreateOrderRequestDto.builder()
                .orderInfo(requestDto)
                .memberNo(memberNo)
                .productNos(productNos)
                .productCount(productCount)
                .productAmount(productAmountMap)
                .productCoupon(productCoupon)
                .productSaleAmount(productSaleAmount)
                .build();

        return orderAdaptor.createOrderRequest(createOrderRequestDto);
    }

    private static void parsingAndInput(List<Long> productNos,
                                        Map<Long, Integer> productCount,
                                        Map<Long, Long> productAmountMap,
                                        Map<Long, Long> productCoupon,
                                        Map<Long, Long> productSaleAmount,
                                        String productCouponInfo) {
        String productSection = productCouponInfo.split(PRODUCT_COUPON_DIVIDER)[0];
        String couponSection = productCouponInfo.split(PRODUCT_COUPON_DIVIDER)[1];

        String[] productInfo = productSection.split(DIVIDER);

        Long productNo = Long.parseLong(productInfo[0]);
        Integer productCnt = Integer.parseInt(productInfo[1]);
        Long productAmount = Long.parseLong(productInfo[2]);

        productNos.add(productNo);
        productCount.put(productNo, productCnt);
        productAmountMap.put(productNo, productAmount);

        if (!couponSection.equals(" ")) {
            couponSection = couponSection.trim();
            String[] couponInfo = couponSection.split(DIVIDER);
            final Long couponNo = Long.parseLong(couponInfo[0]);
            final Long couponDiscount = Long.parseLong(couponInfo[2]);
            productCoupon.put(productNo, couponNo);
            productSaleAmount.put(productNo, couponDiscount);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyInvoiceNo(Long orderNo, String invoiceNo) {
        orderAdaptor.modifyInvoiceNoRequest(orderNo, invoiceNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyStateCode(Long orderNo, String codeName) {
        orderAdaptor.modifyStateCodeRequest(orderNo, codeName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetOrderDetailResponseDto getOrderDetailByNo(Long orderNo) {
        return orderAdaptor.getOrderDetailByOrderNoRequest(orderNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetOrderListForAdminResponseDto> getOrderList(Pageable pageable) {
        return orderAdaptor.getAllOrdersRequest(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetOrderListResponseDto> getOrderListByMemberNo(Long memberNo,
                                                                        Pageable pageable) {
        return orderAdaptor.getAllOrdersByMemberNoRequest(pageable, memberNo);
    }
}
