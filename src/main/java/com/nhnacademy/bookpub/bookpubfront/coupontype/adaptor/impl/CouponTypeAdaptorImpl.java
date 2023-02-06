package com.nhnacademy.bookpub.bookpubfront.coupontype.adaptor.impl;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.coupontype.adaptor.CouponTypeAdaptor;
import com.nhnacademy.bookpub.bookpubfront.coupontype.dto.response.GetCouponTypeResponseDto;
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
    private static final String COUPON_TYPE_URL = "/api/coupon-types";
    private static final String COUPON_TYPE_AUTH_URL = "/token/coupon-types";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetCouponTypeResponseDto> requestCouponTypes() {
        String url = gateWayConfig.getGatewayUrl() + COUPON_TYPE_AUTH_URL;

        ResponseEntity<List<GetCouponTypeResponseDto>> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }
}
