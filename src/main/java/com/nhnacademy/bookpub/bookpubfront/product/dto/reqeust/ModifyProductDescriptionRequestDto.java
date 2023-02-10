package com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 상품 설명 Dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class ModifyProductDescriptionRequestDto {
    private String description;
}
