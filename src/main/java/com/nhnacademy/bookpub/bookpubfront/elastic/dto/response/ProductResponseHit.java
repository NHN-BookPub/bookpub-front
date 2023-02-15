package com.nhnacademy.bookpub.bookpubfront.elastic.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 상품 검색어에 대한 인덱스 조회 dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@Setter
public class ProductResponseHit {
    private ProductHit hits;
}
