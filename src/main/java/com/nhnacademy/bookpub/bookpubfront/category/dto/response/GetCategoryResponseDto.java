package com.nhnacademy.bookpub.bookpubfront.category.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 카테고리 정보 반환을 받기 위한 클래스입니다.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class GetCategoryResponseDto {

    private Integer categoryNo;

    private String categoryName;

    private GetCategoryResponseDto parent;

    private Integer categoryPriority;

    private boolean categoryDisplayed;

}