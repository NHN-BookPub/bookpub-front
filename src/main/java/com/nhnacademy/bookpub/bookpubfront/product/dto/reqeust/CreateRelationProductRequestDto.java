package com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 연관관계 상품 등록 Dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreateRelationProductRequestDto {
    private List<Long> relationProducts;
}
