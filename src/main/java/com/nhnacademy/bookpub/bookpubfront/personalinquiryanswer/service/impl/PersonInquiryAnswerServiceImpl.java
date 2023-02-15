package com.nhnacademy.bookpub.bookpubfront.personalinquiryanswer.service.impl;

import com.nhnacademy.bookpub.bookpubfront.personalinquiryanswer.adaptor.PersonInquiryAnswerAdaptor;
import com.nhnacademy.bookpub.bookpubfront.personalinquiryanswer.dto.request.CreatePersonalInquiryAnswerRequestDto;
import com.nhnacademy.bookpub.bookpubfront.personalinquiryanswer.service.PersonalInquiryAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 1대1문의답변 서비스 구현체입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class PersonInquiryAnswerServiceImpl implements PersonalInquiryAnswerService {
    private final PersonInquiryAnswerAdaptor personInquiryAnswerAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPersonalInquiryAnswer(CreatePersonalInquiryAnswerRequestDto createDto) {
        personInquiryAnswerAdaptor.requestCreatePersonalInquiryAnswer(createDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletePersonalInquiryAnswer(Long answerNo) {
        personInquiryAnswerAdaptor.requestDeletePersonalInquiryAnswer(answerNo);
    }
}
