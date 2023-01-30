package com.nhnacademy.bookpub.bookpubfront.state;

import lombok.Getter;

/**
 * 가격 정책(배송비 or 포장비)를 검증하기 위한 enum 입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
public enum PricePolicyState implements States {
    SHIPPING("배송비"),
    PACKAGING("포장비");
    String name;
    PricePolicyState(String name) {
        this.name = name;
    }

    /**
     * @StateCode 를 사용하기 위한 구현메소드 입니다.
     *
     * @return true를 반환합니다.
     */
    @Override
    public boolean isUsed() {
        return true;
    }
}