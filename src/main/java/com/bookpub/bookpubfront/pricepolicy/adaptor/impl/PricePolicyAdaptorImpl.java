package com.bookpub.bookpubfront.pricepolicy.adaptor.impl;

import static com.bookpub.bookpubfront.config.GateWayConfig.getGatewayUrl;
import com.bookpub.bookpubfront.pricepolicy.adaptor.PricePolicyAdaptor;
import com.bookpub.bookpubfront.pricepolicy.dto.CreatePricePolicyRequestDto;
import com.bookpub.bookpubfront.pricepolicy.dto.GetPricePolicyResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 가격정책 어답터의 구현체입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class PricePolicyAdaptorImpl implements PricePolicyAdaptor {
    private final ObjectMapper mapper;
    private final RestTemplate restTemplate;
    private static final String ORDER_PRODUCT_URL = getGatewayUrl() + "/api/state/pricepolicies";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetPricePolicyResponseDto> getAllPricePolicyCodeRequest() {
        ResponseEntity<List<GetPricePolicyResponseDto>> response =
                restTemplate.exchange(ORDER_PRODUCT_URL,
                        HttpMethod.GET,
                        new HttpEntity<>(getHttpHeaders()),
                        new ParameterizedTypeReference<List<GetPricePolicyResponseDto>>() {});

        return checkError(response).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetPricePolicyResponseDto getPricePolicyByCodeNoRequest(Integer codeNo) {
        String url = ORDER_PRODUCT_URL + "/" + codeNo;
        ResponseEntity<GetPricePolicyResponseDto> response =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        new HttpEntity<>(getHttpHeaders()),
                        new ParameterizedTypeReference<GetPricePolicyResponseDto>() {});

        return checkError(response).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createOrderStateCodeRequest(
            CreatePricePolicyRequestDto requestDto)
            throws JsonProcessingException {
        String request = mapper.writeValueAsString(requestDto);
        HttpEntity<String> httpEntity = new HttpEntity<>(request, getHttpHeaders());
        ResponseEntity<Void> response =
                restTemplate.exchange(ORDER_PRODUCT_URL,
                        HttpMethod.POST,
                        httpEntity,
                        Void.class);

        checkError(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyPricePolicyFeeUsedRequest(Integer codeNo, Long fee) {
        String url = ORDER_PRODUCT_URL + "/" + codeNo + "?fee=" + fee;
        ResponseEntity<Void> response =
                restTemplate.exchange(url,
                        HttpMethod.PUT,
                        new HttpEntity<>(getHttpHeaders()),
                        Void.class);

        checkError(response);
    }

    /**
     * 에러를 체크하기 위한 메서드입니다.
     * 400, 500번대 에러를 거릅니다.
     *
     * @param response ResponseEntity 받습니다.
     * @return 에러가 없으면 그대로 반환합니다.
     * @param <T> 지네릭 타입입니다.
     */
    private <T> ResponseEntity<T> checkError(ResponseEntity<T> response) {
        HttpStatus status = response.getStatusCode();

        if (status.is4xxClientError() || status.is5xxServerError()) {
            throw new RuntimeException();
        }

        return response;
    }


    /**
     * 콘텐트 타입이 지정된 헤더를 반환하는 메서드입니다.
     *
     * @return JSon 헤더를 반환합니다.
     */
    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }


}
