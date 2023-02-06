package com.nhnacademy.bookpub.bookpubfront.pricepolicy.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig.getGatewayUrl;
import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;

import com.nhnacademy.bookpub.bookpubfront.pricepolicy.adaptor.PricePolicyAdaptor;
import com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.request.CreatePricePolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.response.GetOrderPolicyResponseDto;
import com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.response.GetPricePolicyResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 가격정책 어답터의 구현체입니다.
 *
 * @author : 여운석, 임태원
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class PricePolicyAdaptorImpl implements PricePolicyAdaptor {
    private final RestTemplate restTemplate;
    private static final String ORDER_PRODUCT_URL = getGatewayUrl() + "/api/state/pricepolicies";
    private static final String AUTH_ORDER_PRODUCT_URL =
            getGatewayUrl() + "/token/state/pricepolicies";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetPricePolicyResponseDto> getAllPricePolicyCodeRequest() {
        ResponseEntity<List<GetPricePolicyResponseDto>> response =
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
    public void createOrderStateCodeRequest(CreatePricePolicyRequestDto requestDto) {
        HttpEntity<CreatePricePolicyRequestDto> httpEntity =
                new HttpEntity<>(requestDto, makeHeader());
        restTemplate.exchange(AUTH_ORDER_PRODUCT_URL,
                HttpMethod.POST,
                httpEntity,
                Void.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetPricePolicyResponseDto> getPoliciesByName(String name) {
        String url = ORDER_PRODUCT_URL + "/" + name;

        ResponseEntity<List<GetPricePolicyResponseDto>> response =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        new HttpEntity<>(makeHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        return response.getBody();
    }

    @Override
    public List<GetOrderPolicyResponseDto> getShipAndPackagePolicy() {
        return restTemplate.exchange(
                ORDER_PRODUCT_URL + "/order",
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<List<GetOrderPolicyResponseDto>>() {
                }
        ).getBody();
    }
}
