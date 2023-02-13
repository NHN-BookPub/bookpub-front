package com.nhnacademy.bookpub.bookpubfront.state;

import lombok.Getter;

/**
 * 고객 서비스 카테고리입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
public enum CustomerServiceCategory implements States {
    FAQ_USING("faqUsing", true),
    FAQ_MEMBER("faqAccount", true),
    FAQ_PAYMENT("faqPayment", true),
    FAQ_OTHERS("faqOthers", true),
    NOTICE_NORMAL("noteNormal", true),
    NOTICE_SERVER("noteServer", true),
    NOTICE_PAYMENT("notePayment", true),
    NOTICE_OTHERS("noteOthers", true);


    private final String name;
    private final boolean isUsed;

    CustomerServiceCategory(String name, boolean isUsed) {
        this.name = name;
        this.isUsed = true;
    }
}
