package com.nhnacademy.bookpub.bookpubfront.coupon.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;

/**
 * 쿠폰을 조회하기 위한 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
public class GetCouponResponseDto {
    private Long couponNo;
    private String memberId;
    private String templateName;
    private String templateImage;
    private String typeName;
    private boolean policyFixed;
    private Long policyPrice;
    private Long policyMinimum;
    private Long maxDiscount;
    private LocalDateTime finishedAt;
    private boolean couponUsed;
}
