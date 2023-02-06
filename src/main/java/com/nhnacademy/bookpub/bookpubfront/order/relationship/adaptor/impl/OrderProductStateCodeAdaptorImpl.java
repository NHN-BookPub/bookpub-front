package com.nhnacademy.bookpub.bookpubfront.order.relationship.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig.getGatewayUrl;
import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;

import com.nhnacademy.bookpub.bookpubfront.order.relationship.adaptor.OrderProductStateCodeAdaptor;
import com.nhnacademy.bookpub.bookpubfront.order.relationship.dto.CreateOrderProductStateCodeRequestDto;
import com.nhnacademy.bookpub.bookpubfront.order.relationship.dto.GetOrderProductStateCodeResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 주문상품상태코드 어댑터의 구현체입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class OrderProductStateCodeAdaptorImpl implements OrderProductStateCodeAdaptor {
    private final RestTemplate restTemplate;
    private static final String ORDER_PRODUCT_URL = getGatewayUrl() + "/api/state/orderproducts";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetOrderProductStateCodeResponseDto> getAllOrderProductStateCodeRequest() {
        ResponseEntity<List<GetOrderProductStateCodeResponseDto>> response =
                restTemplate.exchange(ORDER_PRODUCT_URL,
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
    public GetOrderProductStateCodeResponseDto getOrderProductStateCodeByCodeNoRequest(Integer codeNo) {
        String url = ORDER_PRODUCT_URL + "/" + codeNo;
        ResponseEntity<GetOrderProductStateCodeResponseDto> response =
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
    public void createOrderProductStateCodeRequest(
            CreateOrderProductStateCodeRequestDto requestDto) {
        HttpEntity<CreateOrderProductStateCodeRequestDto> httpEntity =
                new HttpEntity<>(requestDto, makeHeader());

        restTemplate.exchange(ORDER_PRODUCT_URL,
                HttpMethod.POST,
                httpEntity,
                Void.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyOrderProductStateCodeUsedRequest(Integer codeNo, boolean used) {
        String url = ORDER_PRODUCT_URL + "/" + codeNo + "?used=" + used;
        restTemplate.exchange(url,
                HttpMethod.PUT,
                new HttpEntity<>(makeHeader()),
                Void.class);
    }
}
