package com.nhnacademy.bookpub.bookpubfront.couponstatecode.adaptor.impl;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.couponstatecode.adaptor.CouponStateCodeAdaptor;
import com.nhnacademy.bookpub.bookpubfront.couponstatecode.dto.response.GetCouponStateCodeResponseDto;
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
    private static final String COUPON_STATE_CODE_URL = "/api/coupon-state-codes";
    private static final String COUPON_STATE_CODE_AUTH_URL = "/token/coupon-state-codes";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetCouponStateCodeResponseDto> requestCouponStateCodes() {

        String url = gateWayConfig.getGatewayUrl() + COUPON_STATE_CODE_AUTH_URL;

        ResponseEntity<List<GetCouponStateCodeResponseDto>> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }
}
