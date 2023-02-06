package com.nhnacademy.bookpub.bookpubfront.product.relationship.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig.getGatewayUrl;

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
    private final RestTemplate restTemplate;
    private static final String AUTH_POLICY_URI = "/token/policy/product";
    private static final String PRODUCT_POLICY_URI = "/api/policy/product";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetProductPolicyResponseDto> getProductPolicies() {
        String url = getGatewayUrl() + PRODUCT_POLICY_URI;

        ResponseEntity<List<GetProductPolicyResponseDto>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        new HttpEntity<>(Utils.makeHeader()),
                        new ParameterizedTypeReference<>() {
                        }
                );

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createProductPolicy(CreateProductPolicyRequestDto createRequestDto) {
        String url = getGatewayUrl() + AUTH_POLICY_URI;

        restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(createRequestDto, Utils.makeHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyProductPolicy(Integer policyNo,
                                    ModifyProductPolicyRequestDto modifyRequestDto) {
        String url = getGatewayUrl() + AUTH_POLICY_URI + "/" + policyNo;

        restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(modifyRequestDto, Utils.makeHeader()),
                Void.class
        );
    }
}
