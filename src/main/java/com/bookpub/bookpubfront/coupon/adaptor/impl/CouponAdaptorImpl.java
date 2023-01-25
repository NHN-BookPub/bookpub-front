package com.bookpub.bookpubfront.coupon.adaptor.impl;

import com.bookpub.bookpubfront.config.GateWayConfig;
import com.bookpub.bookpubfront.coupon.adaptor.CouponAdaptor;
import com.bookpub.bookpubfront.coupon.dto.request.CreateCouponRequestDto;
import com.bookpub.bookpubfront.coupon.dto.response.GetCouponResponseDto;
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
 * 쿠폰을 관리하기 위한 어댑터입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class CouponAdaptorImpl implements CouponAdaptor {
    private final RestTemplate restTemplate;
    private static final String COUPON_URL = "/api/coupons";

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestAddCoupon(CreateCouponRequestDto createRequestDto) {
        String url = GateWayConfig.getGatewayUrl() + COUPON_URL;

        HttpEntity<CreateCouponRequestDto> entity = new HttpEntity<>(createRequestDto, Utils.makeHeader());
        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Void.class
        );

        checkError(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetCouponResponseDto> requestCoupons(Pageable pageable, String searchKey, String search) {
        String url = UriComponentsBuilder.fromHttpUrl(GateWayConfig.getGatewayUrl() + COUPON_URL)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("searchKey", searchKey)
                .queryParam("search", search)
                .encode()
                .toUriString();

        ResponseEntity<PageResponse<GetCouponResponseDto>> response = restTemplate.exchange(
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
     * 에러 체크를 위한 메소드입니다.
     *
     * @param response
     * @param <T>
     */
    private static <T> void checkError(ResponseEntity<T> response) {
        HttpStatus statusCode = response.getStatusCode();

        if (statusCode.is4xxClientError() || statusCode.is5xxServerError()) {
            throw new RuntimeException();
        }
    }
}
