package com.nhnacademy.bookpub.bookpubfront.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 최근 본 상품 dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class RecentViewProductDto {
    private Long productNo;
    private String title;
    private String filePath;
}
