package com.nhnacademy.bookpub.bookpubfront.state;

import lombok.Getter;

/**
 * 상품판매 여부에 따른 enum 입니다.
 * 상품판매 상태는 판매중, 품절 중단이 있습니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
public enum ProductSaleState implements States {
    SALE("판매중", true),
    STOP("중단", true),
    SOLD_OUT("품절", true);
    private final String name;
    private final boolean isUsed;

    ProductSaleState(String name, boolean isUsed) {
        this.name = name;
        this.isUsed = isUsed;
    }
}
