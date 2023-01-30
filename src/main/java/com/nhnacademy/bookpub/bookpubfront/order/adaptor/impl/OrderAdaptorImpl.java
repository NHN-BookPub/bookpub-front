package com.nhnacademy.bookpub.bookpubfront.order.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.checkError;
import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.order.adaptor.OrderAdaptor;
import com.nhnacademy.bookpub.bookpubfront.order.dto.CreateOrderRequestDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.GetOrderDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.GetOrderListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.state.OrderState;
import com.nhnacademy.bookpub.bookpubfront.state.anno.StateCode;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 주문 어답터의 구현체입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class OrderAdaptorImpl implements OrderAdaptor {
    private final RestTemplate restTemplate;
    private static final String ORDER_URL = "/api/orders";

    /**
     * {@inheritDoc}
     */
    @Override
    public void createOrderRequest(CreateOrderRequestDto requestDto) {
        String url = GateWayConfig.getGatewayUrl() + ORDER_URL;
        HttpEntity<CreateOrderRequestDto> httpEntity = new HttpEntity<>(requestDto, makeHeader());
        ResponseEntity<Void> response =
                restTemplate.exchange(url, HttpMethod.POST, httpEntity, Void.class);

        checkError(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetOrderListResponseDto> getAllOrdersRequest(Pageable pageable) {
        String url = GateWayConfig.getGatewayUrl() + ORDER_URL
                + "?page=" + pageable.getOffset() + "&size=" + pageable.getPageSize()     ;
        ResponseEntity<PageResponse<GetOrderListResponseDto>> response =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        new HttpEntity<>(makeHeader()),
                        new ParameterizedTypeReference<>(){});

        return (PageResponse<GetOrderListResponseDto>) checkError(response).getBody();
    }

    @Override
    public PageResponse<GetOrderListResponseDto> getAllOrdersByMemberNoRequest(
            Pageable pageable, Long memberNo) {
        String url = GateWayConfig.getGatewayUrl() + ORDER_URL
                + "/member?page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize() + "&no=" + memberNo;

        ResponseEntity<PageResponse<GetOrderListResponseDto>> response =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        new HttpEntity<>(makeHeader()),
                        new ParameterizedTypeReference<>() {});

        return (PageResponse<GetOrderListResponseDto>) checkError(response).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetOrderDetailResponseDto getOrderDetailByOrderNoRequest(Long orderNo) {
        String url = GateWayConfig.getGatewayUrl() + ORDER_URL + "/" + orderNo;
        ResponseEntity<GetOrderDetailResponseDto> response =
                restTemplate.exchange(url, HttpMethod.GET,
                        new HttpEntity<>(makeHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        return (GetOrderDetailResponseDto) checkError(response).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyInvoiceNoRequest(Long orderNo, String invoiceNo) {
        String url = GateWayConfig.getGatewayUrl() + ORDER_URL
                + "/" + orderNo + "/invoice?no=" + invoiceNo;
        ResponseEntity<Void> response =
                restTemplate.exchange(url, HttpMethod.PUT,
                        new HttpEntity<>(makeHeader()),
                        new ParameterizedTypeReference<>() {});

        checkError(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyStateCodeRequest(Long orderNo,
                                       @StateCode(enumClass = OrderState.class)
                                       String codeName) {
        String url = GateWayConfig.getGatewayUrl() + ORDER_URL
                + "/" + orderNo + "/state?no" + codeName;

        ResponseEntity<Void> response =
                restTemplate.exchange(url, HttpMethod.PUT,
                        new HttpEntity<>(makeHeader()),
                        new ParameterizedTypeReference<>() {});

        checkError(response);
    }
}
