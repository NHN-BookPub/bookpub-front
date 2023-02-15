package com.nhnacademy.bookpub.bookpubfront.personalinquiry.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 1대1문의 생성에 필요한 정보를 담은 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreatePersonalInquiryRequestDto {
    private Long memberNo;
    private String inquiryTitle;
    private String inquiryContent;
}
