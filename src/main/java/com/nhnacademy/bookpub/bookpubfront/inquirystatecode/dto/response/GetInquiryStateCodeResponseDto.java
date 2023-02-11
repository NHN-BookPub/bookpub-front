package com.nhnacademy.bookpub.bookpubfront.inquirystatecode.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class GetInquiryStateCodeResponseDto {
    private Integer inquiryCodeNo;

    private String inquiryCodeName;
}
