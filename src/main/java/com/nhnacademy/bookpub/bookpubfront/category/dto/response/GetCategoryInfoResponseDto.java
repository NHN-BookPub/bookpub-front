package com.nhnacademy.bookpub.bookpubfront.category.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 관리자 페이지 좋아요 검색을 위한 카테고리 dto.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class GetCategoryInfoResponseDto {

    private Integer categoryNo;
    private String categoryName;

}
