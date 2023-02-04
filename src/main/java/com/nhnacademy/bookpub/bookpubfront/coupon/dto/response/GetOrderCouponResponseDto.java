package com.nhnacademy.bookpub.bookpubfront.coupon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 주문할 때 필요한 쿠폰 목록 dto.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderCouponResponseDto {
    private Long couponNo;
    private String templateName;
    private Long productNo;
    private Integer categoryNo;
    private boolean policyFixed;
    private Long policyPrice;
    private Long policyMinimum;
    private Long maxDiscount;
    private boolean templateBundled;
}
