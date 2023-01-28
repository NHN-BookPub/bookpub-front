package com.nhnacademy.bookpub.bookpubfront.state;

import lombok.Getter;

/**
 * 쿠폰상태코드를 관리하기위한 enum 입니다.
 * 쿠폰상태종류로는 전체,카테고리,개별상품이 있습니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
public enum CouponState implements States {
    COUPON_ALL("전체", true),
    COUPON_CATEGORY("카테고리", true),
    COUPON_PERSONAL("개별상품", true);
    private final String name;
    private final boolean isUsed;

    CouponState(String name, boolean isUsed) {
        this.name = name;
        this.isUsed = isUsed;
    }
}
