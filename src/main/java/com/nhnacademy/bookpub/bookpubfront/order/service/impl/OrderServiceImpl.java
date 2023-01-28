package com.nhnacademy.bookpub.bookpubfront.order.service.impl;

import com.nhnacademy.bookpub.bookpubfront.order.adaptor.OrderAdaptor;
import com.nhnacademy.bookpub.bookpubfront.order.dto.CreateOrderRequestDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.GetOrderDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.GetOrderListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.service.OrderService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 주문 서비스의 구현체입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderAdaptor orderAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createOrder(CreateOrderRequestDto requestDto) {
        orderAdaptor.createOrderRequest(requestDto);
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
    public PageResponse<GetOrderListResponseDto> getOrderList(Pageable pageable) {
        return orderAdaptor.getAllOrdersRequest(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetOrderListResponseDto> getOrderListByMemberNo(Long memberNo, Pageable pageable) {
        return orderAdaptor.getAllOrdersByMemberNoRequest(pageable, memberNo);
    }
}
