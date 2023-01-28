package com.nhnacademy.bookpub.bookpubfront.category.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 메인 페이지에서 보여줄 카테고리 정보를 담은 dto 입니다.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class GetParentCategoryWithChildrenResponseDto {

    private Integer categoryNo;

    private String categoryName;

    @Setter
    private List<GetChildCategoryResponseDto> childList;

    public GetParentCategoryWithChildrenResponseDto(Integer categoryNo, String categoryName) {
        this.categoryNo = categoryNo;
        this.categoryName = categoryName;
    }
}
