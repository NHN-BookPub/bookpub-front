package com.nhnacademy.bookpub.bookpubfront.state;

import lombok.Getter;

/**
 * 구독상태를 알기위한 enum 입니다.
 * 종류는 구독만료,구독중, 구독취소(환불)가 있습니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
public enum SubscribeState implements States {
    COMPLETE_SUBSCRIPTION("구독만료", true),
    WAITING_SUBSCRIPTION("구독중", true),
    CANCEL_SUBSCRIPTION("구독취소", true);

    private final String name;
    private final boolean isUsed;

    SubscribeState(String name, boolean isUsed) {
        this.name = name;
        this.isUsed = isUsed;
    }
}
