package com.bookpub.bookpubfront.couponpolicy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 쿠폰정책을 조회하기 위해 필요한 정보들을 담을 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetCouponPolicyResponseDto {
    private Integer policyNo;
    private boolean policyFixed;
    private Long discountRate;
    private Long policyMinimum;
    private Long maxDiscount;
}
