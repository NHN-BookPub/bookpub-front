package com.nhnacademy.bookpub.bookpubfront.elastic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 상품 검색에 응답 결과 dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class ProductSearchResultDto {
    private Long id;
    private String title;
    private Long salesPrice;
    private Integer salesRate;
    private String filePath;
}
