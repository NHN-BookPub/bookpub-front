package com.bookpub.bookpubfront.couponpolicy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 쿠폰정책을 수정하기 위해 필요한 정보들을 담을 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class ModifyCouponPolicyRequestDto {
    private Integer policyNo;
    private boolean policyFixed;
    private Long discountRate;
    private Long policyMinimum;
    private Long maxDiscount;
}
