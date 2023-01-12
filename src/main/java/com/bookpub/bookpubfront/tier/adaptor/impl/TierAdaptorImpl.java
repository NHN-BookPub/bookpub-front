package com.bookpub.bookpubfront.tier.adaptor.impl;


import static com.bookpub.bookpubfront.utils.Utils.makeHeader;
import com.bookpub.bookpubfront.config.GateWayConfig;
import com.bookpub.bookpubfront.tier.adaptor.TierAdaptor;
import com.bookpub.bookpubfront.tier.dto.request.CreateTierRequestDto;
import com.bookpub.bookpubfront.tier.dto.request.ModifyTierRequestDto;
import com.bookpub.bookpubfront.tier.dto.response.TierResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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

        String url = gateway.getGatewayUrl() + TIER_URI;
        HttpEntity<String> httpEntity = new HttpEntity<>(request, makeHeader());

        ResponseEntity<Void> response = restTemplate.exchange(url,
                HttpMethod.POST,
                httpEntity,
                Void.class);

        checkError(response);
    }

    /**
     * {@inheritDoc}
     *
     * @throws JsonProcessingException objectMapper 를 통해 변환했을때 예외 발생가능.
     */
    @Override
    public void requestModifyTier(ModifyTierRequestDto modifyTierRequestDto) throws JsonProcessingException {
        String request = objectMapper.writeValueAsString(modifyTierRequestDto);

        String url = gateway.getGatewayUrl() + TIER_URI;
        HttpEntity<String> httpEntity = new HttpEntity<>(request, makeHeader());

        ResponseEntity<Void> response = restTemplate.exchange(url,
                HttpMethod.PUT,
                httpEntity,
                Void.class);


        checkError(response);
    }

    /**
     * {@inheritDoc}
     *
     * @throws JsonProcessingException objectMapper 를 통해 변환했을때 예외 발생가능.
     */
    @Override
    public List<TierResponseDto> requestTierList() {
        String url = gateway.getGatewayUrl() + TIER_URI;


        ResponseEntity<List<TierResponseDto>> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<>() {
                });

        checkError(response);

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TierResponseDto requestTier(Integer tierNo) {

        String url = gateway.getGatewayUrl() + TIER_URI + "/" + tierNo;

        ResponseEntity<TierResponseDto> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<TierResponseDto>() {
                });

        checkError(response);

        return response.getBody();
    }

    // 이부분은 정리가 필요하다
    private static <T> void checkError(ResponseEntity<T> response) {
        HttpStatus statusCode = response.getStatusCode();

        if (statusCode.is4xxClientError() || statusCode.is5xxServerError()) {
            throw new RuntimeException();
        }
    }
}
