package com.nhnacademy.bookpub.bookpubfront.inquirystatecode.adaptor.impl;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.inquirystatecode.adaptor.InquiryStateCodeAdaptor;
import com.nhnacademy.bookpub.bookpubfront.inquirystatecode.dto.response.GetInquiryStateCodeResponseDto;
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
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class InquiryStateCodeAdaptorImpl implements InquiryStateCodeAdaptor {
    private final RestTemplate restTemplate;

    private static final String INQUIRY_STATE_CODE_URL = "/api/inquiry-state-codes";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetInquiryStateCodeResponseDto> requestInquiryStateCodeForMember() {
        String url = GateWayConfig.getGatewayUrl() + INQUIRY_STATE_CODE_URL
                + "/member";

        ResponseEntity<List<GetInquiryStateCodeResponseDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }
}
