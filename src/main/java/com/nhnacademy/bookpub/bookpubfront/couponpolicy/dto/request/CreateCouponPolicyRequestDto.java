package com.nhnacademy.bookpub.bookpubfront.couponpolicy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 쿠폰정책을 등록하기 위해 필요한 정보들을 담을 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreateCouponPolicyRequestDto {
    private boolean policyFixed;
    private Long policyPrice;
    private Long policyMinimum;
    private Long maxDiscount;
}
