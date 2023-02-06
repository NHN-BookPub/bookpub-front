package com.nhnacademy.bookpub.bookpubfront.tier.adaptor.impl;


import static com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig.getGatewayUrl;
import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.bookpub.bookpubfront.tier.adaptor.TierAdaptor;
import com.nhnacademy.bookpub.bookpubfront.tier.dto.request.CreateTierRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tier.dto.request.ModifyTierRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tier.dto.response.TierResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * shop 이랑 연동하기위한 TierAdapter 구현체입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class TierAdaptorImpl implements TierAdaptor {
    private final RestTemplate restTemplate;
    private static final String TIER_URI = getGatewayUrl() + "/api/tiers";
    private static final String AUTH_TIER_URI = getGatewayUrl() + "/token/tiers";

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestAddTier(CreateTierRequestDto createRequestDto) {
        restTemplate.exchange(AUTH_TIER_URI,
                HttpMethod.POST,
                new HttpEntity<>(createRequestDto, makeHeader()),
                Void.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestModifyTier(ModifyTierRequestDto modifyTierRequestDto) {
        restTemplate.exchange(AUTH_TIER_URI,
                HttpMethod.PUT,
                new HttpEntity<>(modifyTierRequestDto, makeHeader()),
                Void.class);
    }

    /**
     * {@inheritDoc}
     *
     * @throws JsonProcessingException objectMapper 를 통해 변환했을때 예외 발생가능.
     */
    @Override
    public List<TierResponseDto> requestTierList() {
        return restTemplate.exchange(
                AUTH_TIER_URI,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<List<TierResponseDto>>() {
                }).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TierResponseDto requestTier(Integer tierNo) {
        String url = TIER_URI + "/" + tierNo;

        return restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<TierResponseDto>() {
                }).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean requestTierName(String tierName) {
        String url = AUTH_TIER_URI + "/check-tierName?tierName=" + tierName;

        return restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                Boolean.class).getBody();
    }
}
