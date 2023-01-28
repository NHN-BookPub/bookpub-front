package com.nhnacademy.bookpub.bookpubfront.category.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 카테고리 생성 정보를 받기 위한 dto 입니다.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreateCategoryRequestDto {

    private String categoryName;

    private Integer parentCategoryNo;

    private Integer categoryPriority;

    private boolean categoryDisplayed;

}
