package com.nhnacademy.bookpub.bookpubfront.personalinquiryanswer.service;

import com.nhnacademy.bookpub.bookpubfront.personalinquiryanswer.dto.request.CreatePersonalInquiryAnswerRequestDto;

/**
 * 1대1문의답변 서비스 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface PersonalInquiryAnswerService {
    /**
     * 1대1문의답변 생성을 위한 메서드입니다.
     *
     * @param createDto 1대1문의답변 생성에 필요한 정보를 담은 Dto
     */
    void createPersonalInquiryAnswer(CreatePersonalInquiryAnswerRequestDto createDto);

    /**
     * 1대1문의답변 삭제를 위한 메서드입니다.
     *
     * @param answerNo 삭제할 1대1문의답변 번호
     */
    void deletePersonalInquiryAnswer(Long answerNo);
}
