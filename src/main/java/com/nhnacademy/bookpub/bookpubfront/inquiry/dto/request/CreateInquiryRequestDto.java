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
    Long inquiryParentNo;
    Long productNo;
    Integer inquiryStateCodeNo;
    String inquiryTitle;
    String inquiryContent;
    boolean inquiryDisplayed;
}
