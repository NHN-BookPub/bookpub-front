package com.nhnacademy.bookpub.bookpubfront.product.relationship.adaptor.impl;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.adaptor.ProductPolicyAdaptor;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.request.CreateProductPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.request.ModifyProductPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.response.GetProductPolicyResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 상품정책 어댑터 구현체.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class ProductPolicyAdaptorImpl implements ProductPolicyAdaptor {

    private final GateWayConfig gateWayConfig;

    private final RestTemplate restTemplate;
    private static final String PRODUCT_POLICY_URI = "/api/policy/product";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetProductPolicyResponseDto> getProductPolicies() {
        String url = gateWayConfig.getGatewayUrl() + PRODUCT_POLICY_URI;

        ResponseEntity<List<GetProductPolicyResponseDto>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        new HttpEntity<>(Utils.makeHeader()),
                        new ParameterizedTypeReference<>() {
                        }
                );

        checkError(response);

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createProductPolicy(CreateProductPolicyRequestDto createRequestDto) {
        String url = gateWayConfig.getGatewayUrl() + PRODUCT_POLICY_URI;

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(createRequestDto, Utils.makeHeader()),
                Void.class
        );

        checkError(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyProductPolicy(Integer policyNo,
                                    ModifyProductPolicyRequestDto modifyRequestDto) {
        String url = gateWayConfig.getGatewayUrl() + PRODUCT_POLICY_URI + "/" + policyNo;

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(modifyRequestDto, Utils.makeHeader()),
                Void.class
        );
    }

    /**
     * 4xx or 5xx 상태코드 응답을 처리하기 위한 메서드.
     *
     * @param response API 응답
     * @param <T>      모든 객체
     */
    private static <T> void checkError(ResponseEntity<T> response) {
        HttpStatus status = response.getStatusCode();

        if (status.is4xxClientError() || status.is5xxServerError()) {
            throw new RuntimeException();
        }
    }
}
