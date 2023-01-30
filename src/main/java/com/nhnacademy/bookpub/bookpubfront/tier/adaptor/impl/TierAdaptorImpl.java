package com.nhnacademy.bookpub.bookpubfront.tier.adaptor.impl;


import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.tier.adaptor.TierAdaptor;
import com.nhnacademy.bookpub.bookpubfront.tier.dto.request.CreateTierRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tier.dto.request.ModifyTierRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tier.dto.response.TierResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
    private final GateWayConfig gateway;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    private static final String TIER_URI = "/api/tiers";

    /**
     * {@inheritDoc}
     *
     * @throws JsonProcessingException objectMapper 를 통해 변환했을때 예외 발생가능.
     */
    @Override
    public void requestAddTier(CreateTierRequestDto createRequestDto) throws JsonProcessingException {
        String request = objectMapper.writeValueAsString(createRequestDto);

        String url = GateWayConfig.getGatewayUrl() + TIER_URI;
        HttpEntity<String> httpEntity = new HttpEntity<>(request, makeHeader());

       restTemplate.exchange(url,
                HttpMethod.POST,
                httpEntity,
                Void.class);
    }

    /**
     * {@inheritDoc}
     *
     * @throws JsonProcessingException objectMapper 를 통해 변환했을때 예외 발생가능.
     */
    @Override
    public void requestModifyTier(ModifyTierRequestDto modifyTierRequestDto) throws JsonProcessingException {
        String request = objectMapper.writeValueAsString(modifyTierRequestDto);

        String url = GateWayConfig.getGatewayUrl() + TIER_URI;
        HttpEntity<String> httpEntity = new HttpEntity<>(request, makeHeader());

        restTemplate.exchange(url,
                HttpMethod.PUT,
                httpEntity,
                Void.class);
    }

    /**
     * {@inheritDoc}
     *
     * @throws JsonProcessingException objectMapper 를 통해 변환했을때 예외 발생가능.
     */
    @Override
    public List<TierResponseDto> requestTierList() {
        String url = GateWayConfig.getGatewayUrl() + TIER_URI;


        ResponseEntity<List<TierResponseDto>> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TierResponseDto requestTier(Integer tierNo) {

        String url = GateWayConfig.getGatewayUrl() + TIER_URI + "/" + tierNo;

        ResponseEntity<TierResponseDto> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<TierResponseDto>() {
                });

        return response.getBody();
    }

}
