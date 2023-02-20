package com.nhnacademy.bookpub.bookpubfront.state;

import lombok.Getter;

/**
 * 통합 검색을 위해 map 자료구조에 사용할 enum class.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
public enum SearchState {
    PRODUCT("product"),
    FAQ("faq"),
    NOTICE("notice");

    private final String key;

    SearchState(String key) {
        this.key = key;
    }
}
