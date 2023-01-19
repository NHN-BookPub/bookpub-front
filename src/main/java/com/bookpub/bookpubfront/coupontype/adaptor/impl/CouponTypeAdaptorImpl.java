package com.bookpub.bookpubfront.coupontype.adaptor.impl;

import com.bookpub.bookpubfront.config.GateWayConfig;
import com.bookpub.bookpubfront.coupontype.adaptor.CouponTypeAdaptor;
import com.bookpub.bookpubfront.coupontype.dto.response.GetCouponTypeResponseDto;
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
 * 관리자 페이지의 쿠폰 유형을 관리하기 위한 어댑터입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class CouponTypeAdaptorImpl implements CouponTypeAdaptor {

    private final GateWayConfig gateWayConfig;
    private final RestTemplate restTemplate;
    private static final String COUPON_TEMPLATE_URL = "/api/coupon-types";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetCouponTypeResponseDto> requestCouponTypes() {
        String url = gateWayConfig.getGatewayUrl() + COUPON_TEMPLATE_URL;

        ResponseEntity<List<GetCouponTypeResponseDto>> response = restTemplate.exchange(url,
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
