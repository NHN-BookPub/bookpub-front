package com.nhnacademy.bookpub.bookpubfront.state;

import lombok.Getter;

/**
 * 문의상태를 관리하기위한 enum 입니다.
 * 신고(불량), 교환, 환불, 일반,답변이 있습니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
public enum InquiryState implements States {
    ERROR("불량", true),
    EXCHANGE("교환", true),
    REFUND("환불", true),
    NORMAL("일반", true),
    ANSWER("답변", true);
    private final String name;
    private final boolean isUsed;

    InquiryState(String name, boolean isUsed) {
        this.name = name;
        this.isUsed = isUsed;
    }
}
