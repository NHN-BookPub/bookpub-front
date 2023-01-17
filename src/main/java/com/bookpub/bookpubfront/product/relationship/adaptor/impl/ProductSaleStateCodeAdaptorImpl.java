package com.bookpub.bookpubfront.product.relationship.adaptor.impl;

import com.bookpub.bookpubfront.config.GateWayConfig;
import com.bookpub.bookpubfront.product.relationship.adaptor.ProductSaleStateCodeAdaptor;
import com.bookpub.bookpubfront.product.relationship.dto.response.GetProductSaleStateCodeResponseDto;
import com.bookpub.bookpubfront.utils.Utils;
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
 * 상품 판매 상태 코드 어댑터 구현체.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class ProductSaleStateCodeAdaptorImpl implements ProductSaleStateCodeAdaptor {

    private final GateWayConfig gateWayConfig;
    private final RestTemplate restTemplate;

    private static final String PRODUCT_SALE_URI = "/api/state/productSale";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetProductSaleStateCodeResponseDto> requestProductSaleStateCodes() {
        String url = gateWayConfig.getGatewayUrl() + PRODUCT_SALE_URI + "/used";

        ResponseEntity<List<GetProductSaleStateCodeResponseDto>> response =
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
