package com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 상품 저자 수정 dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class ModifyProductAuthorRequestDto {
    private List<Integer> authors;
}
