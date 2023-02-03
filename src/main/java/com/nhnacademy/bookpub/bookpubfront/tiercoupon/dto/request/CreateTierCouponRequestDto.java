package com.nhnacademy.bookpub.bookpubfront.tiercoupon.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 등급 쿠폰을 등록하기 위한 DTO.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreateTierCouponRequestDto {

    private Long templateNo;
    private Integer tierNo;

}
