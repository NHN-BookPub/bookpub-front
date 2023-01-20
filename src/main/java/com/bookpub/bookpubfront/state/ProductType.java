package com.bookpub.bookpubfront.state;

import lombok.Getter;

/**
 * 상품 유형을 관리하기 위한 enum class.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
public enum ProductType {

    BEST_SELLER("베스트셀러", 1),
    NEW("신간", 2);

    private final String name;
    private final Integer typeNo;

    ProductType(String name, Integer typeNo) {
        this.name = name;
        this.typeNo = typeNo;
    }
}