package com.nhnacademy.bookpub.bookpubfront.category.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 카테고리 수정을 위한 요청 dto 입니다.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class ModifyCategoryRequestDto {

    private Integer categoryNo;

    private Integer parentCategoryNo;

    private String categoryName;

    private Integer categoryPriority;

    private boolean categoryDisplayed;
}
