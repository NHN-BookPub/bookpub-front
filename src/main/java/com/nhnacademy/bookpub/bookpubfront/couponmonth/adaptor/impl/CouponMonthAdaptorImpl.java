package com.nhnacademy.bookpub.bookpubfront.couponmonth.adaptor.impl;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.couponmonth.adaptor.CouponMonthAdaptor;
import com.nhnacademy.bookpub.bookpubfront.couponmonth.dto.request.CreateCouponMonthRequestDto;
import com.nhnacademy.bookpub.bookpubfront.couponmonth.dto.request.ModifyCouponMonthRequestDto;
import com.nhnacademy.bookpub.bookpubfront.couponmonth.dto.response.GetCouponMonthResponseDto;
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
 * 이달의 쿠폰을 관리하기 위한 어댑터입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class CouponMonthAdaptorImpl implements CouponMonthAdaptor {

    private final RestTemplate restTemplate;
    private static final String COUPON_MONTH_URL = "/api/coupon-months";
    private static final String COUPON_MONTH_AUTH_URL = "/token/coupon-months";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetCouponMonthResponseDto> requestCouponMonths() {
        String url = GateWayConfig.getGatewayUrl() + COUPON_MONTH_URL;

        ResponseEntity<List<GetCouponMonthResponseDto>> response = restTemplate.exchange(
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
    public void requestAddCouponMonth(CreateCouponMonthRequestDto createRequestDto) {
        String url = GateWayConfig.getGatewayUrl() + COUPON_MONTH_AUTH_URL;

        HttpEntity<CreateCouponMonthRequestDto> request = new HttpEntity<>(createRequestDto, Utils.makeHeader());

        restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestModifyCouponMonth(ModifyCouponMonthRequestDto modifyRequestDto) {
        String url = GateWayConfig.getGatewayUrl() + COUPON_MONTH_AUTH_URL;

        HttpEntity<ModifyCouponMonthRequestDto> request = new HttpEntity<>(modifyRequestDto, Utils.makeHeader());

        restTemplate.exchange(
                url,
                HttpMethod.PUT,
                request,
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestDeleteCouponMonth(Long monthNo) {
        String url = GateWayConfig.getGatewayUrl() + COUPON_MONTH_AUTH_URL + "/" + monthNo;

        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                new HttpEntity<>(Utils.makeHeader()),
                Void.class
        );
    }
}
