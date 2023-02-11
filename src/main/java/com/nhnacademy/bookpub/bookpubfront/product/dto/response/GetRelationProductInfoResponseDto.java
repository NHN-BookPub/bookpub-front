package com.nhnacademy.bookpub.bookpubfront.product.dto.response;

import lombok.Getter;

/**
 * 연관관계 상품 정보 dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
public class GetRelationProductInfoResponseDto {
    private Long productNo;
    private String title;
    private String filePath;
    private Long salesPrice;
}
