package com.nhnacademy.bookpub.bookpubfront.state;

import lombok.Getter;

/**
 * 상품유형에 관한 enum 입니다.
 * 종류는 : 베스트셀러, 추천, 신간, 인기, 할인 ,기본이 있습니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
public enum ProductTypeState implements States {
    BEST_SELLER("베스트셀러", true),
    NEW("신간", true),
    RECOMMENDATION("추천", true),
    POPULAR("인기", true),
    DISCOUNT("할인", true),
    NORMAL("기본", true);

    private final String name;
    private final boolean isUsed;

    ProductTypeState(String name, boolean isUsed) {
        this.name = name;
        this.isUsed = isUsed;
    }
}
