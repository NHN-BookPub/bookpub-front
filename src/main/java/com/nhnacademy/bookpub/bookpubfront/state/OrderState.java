package com.nhnacademy.bookpub.bookpubfront.state;

import lombok.Getter;

/**
 * 주문상태를 관리하기위한 enum 입니다.
 * 주문상태는 결제대기,결제완료,결제취소,배송중,배송완료,배송취소 가 있습니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
public enum OrderState implements States {
    WAITING_PAYMENT("결제대기", true),
    COMPLETE_PAYMENT("결제완료", true),
    CANCEL_PAYMENT("결제취소", true),
    WAITING_DELIVERY("배송중", true),
    COMPLETE_DELIVERY("배송완료", true),
    CANCEL_DELIVERY("배송취소", true);


    private final String name;
    private final boolean isUsed;

    OrderState(String name, boolean isUsed) {
        this.name = name;
        this.isUsed = isUsed;
    }
}
