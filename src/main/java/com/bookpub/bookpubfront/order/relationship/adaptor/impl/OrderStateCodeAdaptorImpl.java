package com.bookpub.bookpubfront.order.relationship.adaptor.impl;

import static com.bookpub.bookpubfront.config.GateWayConfig.getGatewayUrl;
import static com.bookpub.bookpubfront.utils.Utils.checkError;
import static com.bookpub.bookpubfront.utils.Utils.makeHeader;
import com.bookpub.bookpubfront.order.relationship.adaptor.OrderStateCodeAdaptor;
import com.bookpub.bookpubfront.order.relationship.dto.CreateOrderStateCodeRequestDto;
import com.bookpub.bookpubfront.order.relationship.dto.GetOrderStateCodeResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 주문상태코드 어답터의 구현체입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class OrderStateCodeAdaptorImpl implements OrderStateCodeAdaptor {
    private final RestTemplate restTemplate;
    private static final String ORDER_PRODUCT_URL = getGatewayUrl() + "/api/state/orderstates";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetOrderStateCodeResponseDto> getAllOrderStateCodeRequest() {
        ResponseEntity<List<GetOrderStateCodeResponseDto>> response =
                restTemplate.exchange(ORDER_PRODUCT_URL,
                        HttpMethod.GET,
                        new HttpEntity<>(makeHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        return (List<GetOrderStateCodeResponseDto>) checkError(response).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetOrderStateCodeResponseDto getOrderStateCodeByCodeNoRequest(Integer codeNo) {
        String url = ORDER_PRODUCT_URL + "/" + codeNo;
        ResponseEntity<GetOrderStateCodeResponseDto> response =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        new HttpEntity<>(makeHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        return (GetOrderStateCodeResponseDto) checkError(response).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createOrderStateCodeRequest(
            CreateOrderStateCodeRequestDto requestDto) {
        HttpEntity<CreateOrderStateCodeRequestDto> httpEntity =
                new HttpEntity<>(requestDto, makeHeader());

        ResponseEntity<Void> response =
                restTemplate.exchange(ORDER_PRODUCT_URL,
                        HttpMethod.POST,
                        httpEntity,
                        Void.class);

        checkError(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyOrderStateCodeUsedRequest(Integer codeNo) {
        String url = ORDER_PRODUCT_URL + "/" + codeNo;
        ResponseEntity<Void> response =
                restTemplate.exchange(url,
                        HttpMethod.PUT,
                        new HttpEntity<>(makeHeader()),
                        Void.class);

        checkError(response);
    }
}
