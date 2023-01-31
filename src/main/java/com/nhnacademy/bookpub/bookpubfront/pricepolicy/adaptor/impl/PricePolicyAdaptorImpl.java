package com.nhnacademy.bookpub.bookpubfront.pricepolicy.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig.getGatewayUrl;
import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.checkError;
import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;

import com.nhnacademy.bookpub.bookpubfront.pricepolicy.adaptor.PricePolicyAdaptor;
import com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.CreatePricePolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.GetPricePolicyResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
                        new HttpEntity<>(makeHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        return (List<GetPricePolicyResponseDto>) checkError(response).getBody();
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
                        new HttpEntity<>(makeHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        return (GetPricePolicyResponseDto) checkError(response).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createOrderStateCodeRequest(
            CreatePricePolicyRequestDto requestDto) {
        HttpEntity<CreatePricePolicyRequestDto> httpEntity = new HttpEntity<>(requestDto, makeHeader());
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
                        new HttpEntity<>(makeHeader()),
                        Void.class);

        checkError(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetPricePolicyResponseDto> getPoliciesByName(String name) {
        String url = ORDER_PRODUCT_URL + "/" + name;

        ResponseEntity<List<GetPricePolicyResponseDto>> response =
                restTemplate.exchange(url,
                        HttpMethod.GET,
                        new HttpEntity<>(makeHeader()),
                        new ParameterizedTypeReference<>() {
                        });

        return (List<GetPricePolicyResponseDto>) checkError(response).getBody();
    }
}
