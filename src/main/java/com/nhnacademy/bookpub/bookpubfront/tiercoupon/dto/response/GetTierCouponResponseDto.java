package com.nhnacademy.bookpub.bookpubfront.tiercoupon.dto.response;

import lombok.Getter;

/**
 * 등급 쿠폰 조회를 위한 DTO.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@Getter
public class GetTierCouponResponseDto {

    private Long templateNo;
    private String templateName;
    private Integer tierNo;
    private String tierName;

}
