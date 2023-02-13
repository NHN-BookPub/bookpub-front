package com.nhnacademy.bookpub.bookpubfront.state;

import lombok.Getter;

/**
 * 상품 유형을 관리하기 위한 Enum class.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
public enum ProductType {

    BEST_SELLER("베스트셀러", 1),
    NEW("신간", 2),
    RECOMMENDATION("추천", 3),
    POPULAR("인기", 4),
    DISCOUNT("할인", 5);



    private final String name;
    private final Integer typeNo;

    ProductType(String name, Integer typeNo) {
        this.name = name;
        this.typeNo = typeNo;
    }
}
