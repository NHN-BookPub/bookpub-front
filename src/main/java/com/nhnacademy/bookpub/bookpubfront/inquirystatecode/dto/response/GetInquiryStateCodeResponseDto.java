package com.nhnacademy.bookpub.bookpubfront.inquirystatecode.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 상품문의상태코드 조회를 위한 dto.
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
