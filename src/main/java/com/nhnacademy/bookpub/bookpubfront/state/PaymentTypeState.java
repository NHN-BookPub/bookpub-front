package com.nhnacademy.bookpub.bookpubfront.state;

import lombok.Getter;

/**
 * 결제유형상태코드를 위한 enum 입니다.
 * 카드결제밖에없지만 늘어날수 있습니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
public enum PaymentTypeState implements States {
    CARD("카드결제", true);
    private final String name;
    private final boolean isUsed;

    PaymentTypeState(String name, boolean isUsed) {
        this.name = name;
        this.isUsed = isUsed;
    }
}
