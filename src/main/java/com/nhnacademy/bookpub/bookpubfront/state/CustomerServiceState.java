package com.nhnacademy.bookpub.bookpubfront.state;

import lombok.Getter;

/**
 * 고객서비스를 위한 enum 입니다.(관리자만 사용합니다)
 * 종류로는 : FAQ, 공지사항이있습니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
public enum CustomerServiceState implements States {
    FAQ("faq", true),
    NOTICE("notice", true);
    private final String name;
    private final boolean isUsed;

    CustomerServiceState(String name, boolean isUsed) {
        this.name = name;
        this.isUsed = isUsed;
    }
}
