package com.nhnacademy.bookpub.bookpubfront.order.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.order.adaptor.OrderAdaptor;
import com.nhnacademy.bookpub.bookpubfront.order.dto.request.CreateOrderRequestDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderAndPaymentResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderListForAdminResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductByCategoryResponseDto;
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
import org.springframework.web.util.UriComponentsBuilder;

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
    private static final String AUTH_ORDER_URL = "/token/orders";

    /**
     * {@inheritDoc}
     */
    @Override
    public Long createOrderRequest(CreateOrderRequestDto requestDto) {
        String url = GateWayConfig.getGatewayUrl() + ORDER_URL;
        HttpEntity<CreateOrderRequestDto> httpEntity = new HttpEntity<>(requestDto, makeHeader());
        ResponseEntity<Long> response =
                restTemplate.exchange(url, HttpMethod.POST, httpEntity, Long.class);

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetOrderListForAdminResponseDto> getAllOrdersRequest(Pageable pageable) {
        String url =
                UriComponentsBuilder.fromHttpUrl(GateWayConfig.getGatewayUrl() + AUTH_ORDER_URL)
                        .queryParam("page", pageable.getPageNumber())
                        .queryParam("size", pageable.getPageSize())
                        .encode()
                        .toUriString();

        ResponseEntity<PageResponse<GetOrderListForAdminResponseDto>> response =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        new HttpEntity<>(makeHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        return response.getBody();
    }

    @Override
    public PageResponse<GetOrderListResponseDto> getAllOrdersByMemberNoRequest(
            Pageable pageable, Long memberNo) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + AUTH_ORDER_URL + "/member/" + memberNo)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        ResponseEntity<PageResponse<GetOrderListResponseDto>> response =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        new HttpEntity<>(makeHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetOrderDetailResponseDto getOrderDetailByOrderNoRequest(Long orderNo) {
        String url = GateWayConfig.getGatewayUrl() + AUTH_ORDER_URL + "/" + orderNo;
        ResponseEntity<GetOrderDetailResponseDto> response =
                restTemplate.exchange(url, HttpMethod.GET,
                        new HttpEntity<>(makeHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetOrderDetailResponseDto getOrderDetailByOrderIdRequest(
            String orderId, String phoneNo) {
        String url = GateWayConfig.getGatewayUrl()
                + ORDER_URL + "/non/" + orderId + "?phoneNo=" + phoneNo;
        ResponseEntity<GetOrderDetailResponseDto> response =
                restTemplate.exchange(url, HttpMethod.GET,
                        new HttpEntity<>(makeHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyInvoiceNoRequest(Long orderNo, String invoiceNo) {
        String url = GateWayConfig.getGatewayUrl() + AUTH_ORDER_URL
                + "/" + orderNo + "/invoice?no=" + invoiceNo;
        restTemplate.exchange(url, HttpMethod.PUT,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<>() {
                });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyStateCodeRequest(Long orderNo,
                                       @StateCode(enumClass = OrderState.class)
                                       String codeName) {
        String url = GateWayConfig.getGatewayUrl() + AUTH_ORDER_URL
                + "/" + orderNo + "/state?no" + codeName;


        restTemplate.exchange(url, HttpMethod.PUT,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<>() {
                });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetOrderAndPaymentResponseDto getOrderAndPaymentInfo(String orderId) {
        String url = UriComponentsBuilder.fromHttpUrl(GateWayConfig.getGatewayUrl() + ORDER_URL)
                .path("/payment")
                .path("/" + orderId).build().toUriString();
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                GetOrderAndPaymentResponseDto.class
        ).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetProductByCategoryResponseDto> getEbooksByMember(
            Pageable pageable, Long memberNo) {
        String url = UriComponentsBuilder.fromHttpUrl(GateWayConfig.getGatewayUrl()
                        + "/token/product/" + memberNo + "/ebooks/")
                .build()
                .toUriString();

        return restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        new HttpEntity<>(makeHeader()),
                        new ParameterizedTypeReference<
                                PageResponse<GetProductByCategoryResponseDto>>() {})
                .getBody();
    }

    @Override
    public ResponseEntity<Void> refundOrder(Long orderNo) {
        return null;
    }
}
