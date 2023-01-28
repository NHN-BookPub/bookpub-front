package com.nhnacademy.bookpub.bookpubfront.couponstatecode.dto.response;

import lombok.Getter;

/**
 * 쿠폰상태코드 조회를 위한 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
public class GetCouponStateCodeResponseDto {
    private Integer codeNo;
    private String codeTarget;
}
