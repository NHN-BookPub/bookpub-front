package com.nhnacademy.bookpub.bookpubfront.couponpolicy.adaptor.impl;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.couponpolicy.adaptor.CouponPolicyAdaptor;
import com.nhnacademy.bookpub.bookpubfront.couponpolicy.dto.request.CreateCouponPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.couponpolicy.dto.request.ModifyCouponPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.couponpolicy.dto.response.GetCouponPolicyResponseDto;
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
 * api를 이용해 back서버(shop)와 데이터를 주고받기 위해 만든 어댑터 구현체입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class CouponPolicyAdaptorImpl implements CouponPolicyAdaptor {
    private final GateWayConfig gateWayConfig;
    private final RestTemplate restTemplate;
    private static final String COUPON_POLICY_URL = "/api/coupon-policies";
    private static final String COUPON_POLICY_AUTH_URL = "/token/coupon-policies";

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestAddCouponPolicy(CreateCouponPolicyRequestDto createRequestDto) {

        String url = gateWayConfig.getGatewayUrl() + COUPON_POLICY_AUTH_URL;

        HttpEntity<CreateCouponPolicyRequestDto> httpEntity =
                new HttpEntity<>(createRequestDto, Utils.makeHeader());

        restTemplate.exchange(url,
                HttpMethod.POST,
                httpEntity,
                Void.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestModifyCouponPolicy(ModifyCouponPolicyRequestDto modifyRequestDto) {

        String url = gateWayConfig.getGatewayUrl() + COUPON_POLICY_AUTH_URL;

        HttpEntity<ModifyCouponPolicyRequestDto> httpEntity =
                new HttpEntity<>(modifyRequestDto, Utils.makeHeader());

        restTemplate.exchange(url,
                HttpMethod.PUT,
                httpEntity,
                Void.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetCouponPolicyResponseDto requestCouponPolicy(Integer policyNo) {
        String url = gateWayConfig.getGatewayUrl() + COUPON_POLICY_AUTH_URL + "/" + policyNo;

        ResponseEntity<GetCouponPolicyResponseDto> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                GetCouponPolicyResponseDto.class);

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetCouponPolicyResponseDto> requestCouponPolicies() {
        String url = gateWayConfig.getGatewayUrl() + COUPON_POLICY_AUTH_URL;

        ResponseEntity<List<GetCouponPolicyResponseDto>> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }
}
