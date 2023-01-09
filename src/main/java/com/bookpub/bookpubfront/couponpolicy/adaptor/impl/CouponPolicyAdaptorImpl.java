package com.bookpub.bookpubfront.couponpolicy.adaptor.impl;

import com.bookpub.bookpubfront.config.GateWayConfig;
import com.bookpub.bookpubfront.couponpolicy.adaptor.CouponPolicyAdaptor;
import com.bookpub.bookpubfront.couponpolicy.dto.request.CreateCouponPolicyRequestDto;
import com.bookpub.bookpubfront.couponpolicy.dto.request.ModifyCouponPolicyRequestDto;
import com.bookpub.bookpubfront.couponpolicy.dto.response.GetCouponPolicyResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
 * api를 이용해 back서버(shop)와 데이터를 주고받기 위해 만든 어댑터 구현체입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class CouponPolicyAdaptorImpl implements CouponPolicyAdaptor {
    private final GateWayConfig gateWayConfig;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private static final String COUPON_POLICY_URL = "/api/coupon-policies";

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestAddCouponPolicy(CreateCouponPolicyRequestDto createRequestDto)
            throws JsonProcessingException {
        String request = objectMapper.writeValueAsString(createRequestDto);

        String url = gateWayConfig.getGatewayUrl() + COUPON_POLICY_URL;

        HttpEntity<String> httpEntity = new HttpEntity<>(request, makeHeaders());

        ResponseEntity<Void> response = restTemplate.exchange(url,
                HttpMethod.POST,
                httpEntity,
                Void.class);

        checkError(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestModifyCouponPolicy(ModifyCouponPolicyRequestDto modifyRequestDto)
            throws JsonProcessingException {
        String request = objectMapper.writeValueAsString(modifyRequestDto);

        String url = gateWayConfig.getGatewayUrl() + COUPON_POLICY_URL;

        HttpEntity<String> httpEntity = new HttpEntity<>(request, makeHeaders());

        ResponseEntity<Void> response = restTemplate.exchange(url,
                HttpMethod.PUT,
                httpEntity,
                Void.class);

        checkError(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetCouponPolicyResponseDto requestCouponPolicy(Integer policyNo) {
        String url = gateWayConfig.getGatewayUrl() + COUPON_POLICY_URL + "/" + policyNo;

        ResponseEntity<GetCouponPolicyResponseDto> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeaders()),
                GetCouponPolicyResponseDto.class);

        checkError(response);

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetCouponPolicyResponseDto> requestCouponPolicies() {
        String url = gateWayConfig.getGatewayUrl() + COUPON_POLICY_URL;

        ResponseEntity<List<GetCouponPolicyResponseDto>> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeaders()),
                new ParameterizedTypeReference<>() {
                });

        checkError(response);

        return response.getBody();
    }

    private static <T> void checkError(ResponseEntity<T> response) {
        HttpStatus statusCode = response.getStatusCode();

        if (statusCode.is4xxClientError() || statusCode.is5xxServerError()) {
            throw new RuntimeException();
        }
    }

    private static HttpHeaders makeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }
}
