package com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 상품 태그 수정을 위한 dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class ModifyProductTagRequestDto {
    private List<Integer> tags;
}
