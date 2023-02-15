package com.nhnacademy.bookpub.bookpubfront.personalinquiryanswer.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 1대1문의답변을 생성하기 위한 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreatePersonalInquiryAnswerRequestDto {
    private Long personalInquiryNo;
    private String personalInquiryAnswerContent;
}
