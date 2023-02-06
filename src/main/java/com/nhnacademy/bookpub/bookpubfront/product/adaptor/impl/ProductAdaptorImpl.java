package com.nhnacademy.bookpub.bookpubfront.product.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.coupon.dto.response.GetOrderCouponResponseDto;
import com.nhnacademy.bookpub.bookpubfront.main.dto.response.GetProductByTypeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.adaptor.ProductAdaptor;
import com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust.CreateProductRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductByCategoryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * ProductAdaptor 구현체.
 *
 * @author : 박경서, 정유진, 김서현
 * @since : 1.0
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class ProductAdaptorImpl implements ProductAdaptor {

    private final RestTemplate restTemplate;
    private static final String PRODUCT_URI = "/api/products";
    private static final String AUTH_PRODUCT_URI = "/token/products";

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestCreateProduct(CreateProductRequestDto requestDto,
                                     Map<String, MultipartFile> fileMap) {
        String url = GateWayConfig.getGatewayUrl() + AUTH_PRODUCT_URI;

        MultiValueMap<String, Object> mapRequest = new LinkedMultiValueMap<>();
        mapRequest.add("requestDto", requestDto);
        mapRequest.add("thumbnail", fileMap.get("thumbnail").getResource());
        mapRequest.add("detail", fileMap.get("detail").getResource());
        mapRequest.add("ebook", fileMap.get("ebook").getResource());

        HttpEntity<MultiValueMap<String, Object>> entity =
                new HttpEntity<>(mapRequest,
                        makeHeader(MediaType.MULTIPART_FORM_DATA,
                                List.of(MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON)));

        restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetProductListResponseDto> requestProducts(Pageable pageable) {
        String url = UriComponentsBuilder.fromHttpUrl(GateWayConfig.getGatewayUrl() + PRODUCT_URI)
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
                        GateWayConfig.getGatewayUrl() + AUTH_PRODUCT_URI
                                + productNo)
                .encode().toUriString();

        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                new HttpEntity<>(Utils.makeHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetProductDetailResponseDto requestProductDetail(Long productNo) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + PRODUCT_URI + "/" + productNo)
                .encode().toUriString();
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                GetProductDetailResponseDto.class
        ).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetProductByTypeResponseDto> requestProductByType(Integer typeNo, Integer limit) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + PRODUCT_URI + "/types/" + typeNo)
                .queryParam("limit", limit)
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<List<GetProductByTypeResponseDto>>() {
                }
        ).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetProductDetailResponseDto> requestProductInCart(List<Long> productsNo) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + PRODUCT_URI + "/cart")
                .queryParam("productNo", productsNo)
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<List<GetProductDetailResponseDto>>() {
                }
        ).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetProductByCategoryResponseDto> requestProductsByCategory(
            Integer categoryNo, Pageable pageable) {

        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + PRODUCT_URI
                                + "/product/categories/" + categoryNo)
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<PageResponse<GetProductByCategoryResponseDto>>() {
                }
        ).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<GetOrderCouponResponseDto>> requestOrderCoupons(
            Long productNo, Long memberNo) {

        String requestUrl = UriComponentsBuilder
                .fromHttpUrl(GateWayConfig.getGatewayUrl() + "/api/coupons/members/"
                        + memberNo + "/order")
                .queryParam("productNo", productNo)
                .encode()
                .toUriString();

        return restTemplate.exchange(
                requestUrl,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<>() {
                });
    }
}
