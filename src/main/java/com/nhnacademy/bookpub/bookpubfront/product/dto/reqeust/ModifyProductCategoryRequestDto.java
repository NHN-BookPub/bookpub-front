package com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 상품 카테고리 수정을 위한 dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class ModifyProductCategoryRequestDto {
    private String categoryOne;
    private String categoryTwo;
    private String categoryThree;
}
