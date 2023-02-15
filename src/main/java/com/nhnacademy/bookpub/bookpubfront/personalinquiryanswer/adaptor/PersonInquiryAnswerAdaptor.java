package com.nhnacademy.bookpub.bookpubfront.personalinquiryanswer.adaptor;

import com.nhnacademy.bookpub.bookpubfront.personalinquiryanswer.dto.request.CreatePersonalInquiryAnswerRequestDto;

/**
 * shop 서버와 통신하기 위한 1대1문의답변 어댑터 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface PersonInquiryAnswerAdaptor {
    void requestCreatePersonalInquiryAnswer(CreatePersonalInquiryAnswerRequestDto createDto);

    void requestDeletePersonalInquiryAnswer(Long answerNo);
}
