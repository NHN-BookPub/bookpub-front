package com.nhnacademy.bookpub.bookpubfront.state;

import lombok.Getter;

/**
 * 주문상품의 상태를 관리하기위한 enum 입니다.
 * 구매확정, 환불,,교환중,교환완료,교환취소 이 있습니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
public enum OrderProductState implements States {
    COMPLETE("결제완료", true),
    CONFIRMED("구매확정", true),
    REFUND("환불", true),
    WAITING_EXCHANGE("교환중", true),
    COMPLETE_EXCHANGE("교환완료", true),
    CANCEL_EXCHANGE("교환취소", true);
    private final String name;
    private final boolean isUsed;

    OrderProductState(String name, boolean isUsed) {
        this.name = name;
        this.isUsed = isUsed;
    }
}
