package com.nhnacademy.bookpub.bookpubfront.category.util;

import com.nhnacademy.bookpub.bookpubfront.category.dto.response.GetParentCategoryWithChildrenResponseDto;
import com.nhnacademy.bookpub.bookpubfront.category.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 * 카테고리 뷰를 위한 조회 Utils.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class CategoryUtils {

    private final CategoryService categoryService;

    /**
     * 카테고리 조회 메소드.
     *
     * @param model model
     */
    public void categoriesView(Model model) {
        List<GetParentCategoryWithChildrenResponseDto> category =
                categoryService.getParentCategoryWithChildren();

        model.addAttribute("category", category);
    }
}
