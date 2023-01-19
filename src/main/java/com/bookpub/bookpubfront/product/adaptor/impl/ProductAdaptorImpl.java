package com.bookpub.bookpubfront.product.adaptor.impl;

import com.bookpub.bookpubfront.config.GateWayConfig;
import com.bookpub.bookpubfront.product.adaptor.ProductAdaptor;
import com.bookpub.bookpubfront.product.dto.reqeust.CreateProductRequestDto;
import com.bookpub.bookpubfront.product.dto.response.GetProductDetailResponseDto;
import com.bookpub.bookpubfront.product.dto.response.GetProductListResponseDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import com.bookpub.bookpubfront.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * ProductAdaptor 구현체.
 *
 * @author : 박경서, 정유진, 김서현
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class ProductAdaptorImpl implements ProductAdaptor {

    private final GateWayConfig gateWayConfig;
    private final RestTemplate restTemplate;
    private static final String PRODUCT_URI = "/api/products";

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestCreateProduct(CreateProductRequestDto request) {
        String url = gateWayConfig.getGatewayUrl() + PRODUCT_URI;

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(request, Utils.makeHeader()),
                Void.class
        );

        checkError(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetProductListResponseDto> requestProducts(Pageable pageable) {
        String url = UriComponentsBuilder.fromHttpUrl(gateWayConfig.getGatewayUrl() + PRODUCT_URI)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<PageResponse<GetProductListResponseDto>>() {
                }
        ).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestSetProductDeleted(Long productNo) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        gateWayConfig.getGatewayUrl() + PRODUCT_URI + "/deleted/" + productNo)
                .encode().toUriString();

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(Utils.makeHeader()),
                Void.class
        );

        checkError(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetProductDetailResponseDto requestProductDetail(Long productNo) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        gateWayConfig.getGatewayUrl() + PRODUCT_URI + "/" + productNo)
                .encode().toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                GetProductDetailResponseDto.class
        ).getBody();
    }

    /**
     * 4xx or 5xx 상태코드를 다루는 메서드.
     *
     * @param response 상태 코드
     * @param <T>      모든 타입
     */
    private static <T> void checkError(ResponseEntity<T> response) {
        HttpStatus status = response.getStatusCode();

        if (status.is4xxClientError() || status.is5xxServerError()) {
            throw new RuntimeException();
        }
    }
}
