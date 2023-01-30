package com.nhnacademy.bookpub.bookpubfront.couponstatecode.adaptor.impl;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.couponstatecode.adaptor.CouponStateCodeAdaptor;
import com.nhnacademy.bookpub.bookpubfront.couponstatecode.dto.response.GetCouponStateCodeResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 관리자 페이지의 쿠폰상태코드를 관리하기 위한 어댑터입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class CouponStateCodeAdaptorImpl implements CouponStateCodeAdaptor {
    private final GateWayConfig gateWayConfig;
    private final RestTemplate restTemplate;
    private static final String COUPON_TEMPLATE_URL = "/api/coupon-state-codes";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetCouponStateCodeResponseDto> requestCouponStateCodes() {

        String url = gateWayConfig.getGatewayUrl() + COUPON_TEMPLATE_URL;

        ResponseEntity<List<GetCouponStateCodeResponseDto>> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeaders()),
                new ParameterizedTypeReference<>() {
                });

        checkError(response);

        return response.getBody();
    }

    private static HttpHeaders makeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }

    private static <T> void checkError(ResponseEntity<T> response) {
        HttpStatus statusCode = response.getStatusCode();

        if (statusCode.is4xxClientError() || statusCode.is5xxServerError()) {
            throw new RuntimeException();
        }
    }
}
