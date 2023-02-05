package com.nhnacademy.bookpub.bookpubfront.product.relationship.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig.getGatewayUrl;

import com.nhnacademy.bookpub.bookpubfront.product.relationship.adaptor.ProductTypeStateCodeAdaptor;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.response.GetProductTypeStateCodeResponseDto;
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
 * 상품 타입 상태 코드 어댑터 구현체.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class ProductTypeStateCodeAdaptorImpl implements ProductTypeStateCodeAdaptor {
    private final RestTemplate restTemplate;
    private static final String PRODUCT_TYPE_URI = "/api/state/productType";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetProductTypeStateCodeResponseDto> requestProductTypeStateCodes() {
        String url = getGatewayUrl() + PRODUCT_TYPE_URI + "/used";

        ResponseEntity<List<GetProductTypeStateCodeResponseDto>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        new HttpEntity<>(Utils.makeHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        return response.getBody();
    }
}
