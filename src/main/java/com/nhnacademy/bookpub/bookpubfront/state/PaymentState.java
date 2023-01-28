package com.nhnacademy.bookpub.bookpubfront.state;

import lombok.Getter;

/**
 * 결제상태를 알기위한 enum 입니다.
 * 종류로는 결제승인, 결제거절, 결제대기
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
public enum PaymentState implements States {
    COMPLETE_PAYMENT("결제승인", true),
    CANCEL_PAYMENT("결제거절", true),
    WAITING_PAYMENT("결제대기", true);
    private final String name;
    private final boolean isUsed;

    PaymentState(String name, boolean isUsed) {
        this.name = name;
        this.isUsed = isUsed;
    }
}
