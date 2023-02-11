package com.nhnacademy.bookpub.bookpubfront.product.dto.reqeust;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * API 보내기 위헤 가공할 dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class RestModifyProductCategoryRequestDto {
    private List<Integer> categoriesNo = new ArrayList<>();
}
