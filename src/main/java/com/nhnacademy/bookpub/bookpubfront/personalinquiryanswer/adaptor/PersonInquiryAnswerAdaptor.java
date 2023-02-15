package com.nhnacademy.bookpub.bookpubfront.personalinquiryanswer.adaptor;

import com.nhnacademy.bookpub.bookpubfront.personalinquiryanswer.dto.request.CreatePersonalInquiryAnswerRequestDto;

/**
 * shop 서버와 통신하기 위한 1대1문의답변 어댑터 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface PersonInquiryAnswerAdaptor {
    /**
     * 1대1문의답변 생성을 위한 메서드입니다.
     *
     * @param createDto 1대1문의답변 생성에 필요한 정보를 담은 dto
     */
    void requestCreatePersonalInquiryAnswer(CreatePersonalInquiryAnswerRequestDto createDto);

    /**
     * 1대1문의답변 삭제를 위한 메서드입니다.
     *
     * @param answerNo 삭제할 1대1문의답변 번호
     */
    void requestDeletePersonalInquiryAnswer(Long answerNo);
}
