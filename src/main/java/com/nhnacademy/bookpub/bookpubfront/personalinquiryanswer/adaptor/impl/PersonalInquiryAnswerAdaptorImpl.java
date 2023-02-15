package com.nhnacademy.bookpub.bookpubfront.personalinquiryanswer.adaptor.impl;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.personalinquiryanswer.adaptor.PersonInquiryAnswerAdaptor;
import com.nhnacademy.bookpub.bookpubfront.personalinquiryanswer.dto.request.CreatePersonalInquiryAnswerRequestDto;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * shop 서버와 통신하기 위한 1대1문의답변 어댑터 구현체입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class PersonalInquiryAnswerAdaptorImpl implements PersonInquiryAnswerAdaptor {
    private final RestTemplate restTemplate;
    private static final String TOKEN_PERSONAL_INQUIRY_ANSWER_API =
            "/token/personal-inquiry-answers";

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestCreatePersonalInquiryAnswer(
            CreatePersonalInquiryAnswerRequestDto createDto) {
        String url = GateWayConfig.getGatewayUrl() + TOKEN_PERSONAL_INQUIRY_ANSWER_API;

        restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(createDto, Utils.makeHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestDeletePersonalInquiryAnswer(Long answerNo) {
        String url = GateWayConfig.getGatewayUrl() + TOKEN_PERSONAL_INQUIRY_ANSWER_API
                + "/" + answerNo;

        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                new HttpEntity<>(Utils.makeHeader()),
                Void.class
        );
    }
}
