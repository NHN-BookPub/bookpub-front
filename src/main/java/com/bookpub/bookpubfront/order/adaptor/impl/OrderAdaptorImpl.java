package com.bookpub.bookpubfront.order.adaptor.impl;

import com.bookpub.bookpubfront.config.GateWayConfig;
import com.bookpub.bookpubfront.order.adaptor.OrderAdaptor;
import com.bookpub.bookpubfront.order.dto.CreateOrderRequestDto;
import com.bookpub.bookpubfront.order.dto.GetOrderDetailResponseDto;
import com.bookpub.bookpubfront.order.dto.GetOrderListResponseDto;
import com.bookpub.bookpubfront.state.OrderState;
import com.bookpub.bookpubfront.state.anno.StateCode;
import com.bookpub.bookpubfront.utils.PageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
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
    private final ObjectMapper mapper;
    private final RestTemplate restTemplate;
    private static final String ORDER_URL = "/api/orders";

    /**
     * {@inheritDoc}
     */
    @Override
    public void createOrderRequest(CreateOrderRequestDto requestDto)
            throws JsonProcessingException {
        String request = mapper.writeValueAsString(requestDto);
        String url = GateWayConfig.getGatewayUrl() + ORDER_URL;
        HttpEntity<String> httpEntity = new HttpEntity<>(request, getHttpHeaders());
        ResponseEntity<Void> response =
                restTemplate.exchange(url, HttpMethod.POST, httpEntity, Void.class);

        checkError(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetOrderListResponseDto> getAllOrdersRequest(@Min(0) Integer page) {
        String url = GateWayConfig.getGatewayUrl() + ORDER_URL
                + "?page=" + page + "&size=10";
        ResponseEntity<PageResponse<GetOrderListResponseDto>> response =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        new HttpEntity<>(getHttpHeaders()),
                        new ParameterizedTypeReference<>(){});

        return checkError(response).getBody();
    }

    @Override
    public PageResponse<GetOrderListResponseDto> getAllOrdersByMemberNoRequest(
            @Min(0) Integer page, Long memberNo) {
        String url = GateWayConfig.getGatewayUrl() + ORDER_URL
                + "/member?page=" + page + "&size=10&no=" + memberNo;

        ResponseEntity<PageResponse<GetOrderListResponseDto>> response =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        new HttpEntity<>(getHttpHeaders()),
                        new ParameterizedTypeReference<>() {});

        return checkError(response).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetOrderDetailResponseDto getOrderDetailByOrderNoRequest(Long orderNo) {
        String url = GateWayConfig.getGatewayUrl() + ORDER_URL + "/" + orderNo;
        ResponseEntity<GetOrderDetailResponseDto> response =
                restTemplate.exchange(url, HttpMethod.GET,
                        new HttpEntity<>(getHttpHeaders()),
                        new ParameterizedTypeReference<GetOrderDetailResponseDto>() {});

        return checkError(response).getBody();
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
                        new HttpEntity<>(getHttpHeaders()),
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
                        new HttpEntity<>(getHttpHeaders()),
                        new ParameterizedTypeReference<>() {});

        checkError(response);
    }

    /**
     * 에러를 체크하기 위한 메서드입니다.
     * 400, 500번대 에러를 거릅니다.
     *
     * @param response ResponseEntity 받습니다.
     * @return 에러가 없으면 그대로 반환합니다.
     * @param <T> 지네릭 타입입니다.
     */
    private <T> ResponseEntity<T> checkError(ResponseEntity<T> response) {
        HttpStatus status = response.getStatusCode();

        if (status.is4xxClientError() || status.is5xxServerError()) {
            throw new RuntimeException();
        }

        return response;
    }


    /**
     * 콘텐트 타입이 지정된 헤더를 반환하는 메서드입니다.
     *
     * @return JSon 헤더를 반환합니다.
     */
    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }
}
