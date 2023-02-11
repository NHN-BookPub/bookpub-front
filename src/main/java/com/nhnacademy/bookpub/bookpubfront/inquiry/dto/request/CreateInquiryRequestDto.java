package com.nhnacademy.bookpub.bookpubfront.inquiry.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreateInquiryRequestDto {
    private Long inquiryParentNo;
    private Long productNo;
    private Integer inquiryStateCodeNo;
    private String inquiryTitle;
    private String inquiryContent;
    private boolean inquiryDisplayed;
}
