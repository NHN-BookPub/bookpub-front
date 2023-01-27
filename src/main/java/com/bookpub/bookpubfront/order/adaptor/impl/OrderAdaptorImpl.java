package com.bookpub.bookpubfront.order.adaptor.impl;

import com.bookpub.bookpubfront.config.GateWayConfig;
import com.bookpub.bookpubfront.order.adaptor.OrderAdaptor;
import com.bookpub.bookpubfront.order.dto.request.CreateOrderRequestDto;
import com.bookpub.bookpubfront.order.dto.response.GetOrderDetailResponseDto;
import com.bookpub.bookpubfront.order.dto.response.GetOrderListResponseDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private final ObjectMapper mapper;
    private final RestTemplate restTemplate;
    private static final String ORDER_URL = "/api/orders";

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

    @Override
    public PageResponse<GetOrderListResponseDto> getAllOrdersRequest(@Min(0) Integer page) {
        String url = GateWayConfig.getGatewayUrl() + ORDER_URL
                + "?page=" + page + "&size=10";
        ResponseEntity<PageResponse<GetOrderListResponseDto>> response =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        new HttpEntity<>(getHttpHeaders()),
                        new ParameterizedTypeReference<>() {
                        });

        return checkError(response).getBody();
    }

    @Override
    public GetOrderDetailResponseDto getOrderDetailByOrderNoRequest(Long orderNo) {
        String url = GateWayConfig.getGatewayUrl() + ORDER_URL + "/" + orderNo;
        ResponseEntity<GetOrderDetailResponseDto> response =
                restTemplate.exchange(url, HttpMethod.GET,
                        new HttpEntity<>(getHttpHeaders()),
                        new ParameterizedTypeReference<GetOrderDetailResponseDto>() {
                        });

        return checkError(response).getBody();
    }

    private <T> ResponseEntity<T> checkError(ResponseEntity<T> response) {
        HttpStatus status = response.getStatusCode();

        if (status.is4xxClientError() || status.is5xxServerError()) {
            throw new RuntimeException();
        }

        return response;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }
}
