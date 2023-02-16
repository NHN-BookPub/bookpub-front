package com.nhnacademy.bookpub.bookpubfront.state;

import lombok.Getter;

/**
 * 배송비 관리를 위한 enum class.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
public enum DeliveryFeeType {
    DELIVERY_FREE_FEE_STANDARD("무료 기준", 30000),
    DELIVERY_FEE("배송비", 3000);

    private final String standard;
    private final Integer fee;

    DeliveryFeeType(String standard, Integer fee) {
        this.standard = standard;
        this.fee = fee;
    }
}
