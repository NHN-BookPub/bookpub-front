package com.nhnacademy.bookpub.bookpubfront.elastic.dto.response;

import java.util.ArrayList;
import java.util.List;
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
public class ProductHit {
    private List<Hits> hits;

    /**
     * Hits dto.
     */
    @Getter
    @Setter
    public static class Hits {
        private List<ProductInfo> _source = new ArrayList<>();
    }

    /**
     * 상품 정보 dto.
     */
    @Getter
    @Setter
    public static class ProductInfo {
        private Long id;
        private String title;
        private Long salesprice;
        private Integer salesrate;
        private String filepath;
    }
}
