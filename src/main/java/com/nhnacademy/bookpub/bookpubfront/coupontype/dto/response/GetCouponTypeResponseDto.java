package com.nhnacademy.bookpub.bookpubfront.coupontype.dto.response;

import lombok.Getter;

/**
 * 쿠폰유형을 조회하기 위한 정보를 담은 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
public class GetCouponTypeResponseDto {
    private Long typeNo;
    private String typeName;
}
