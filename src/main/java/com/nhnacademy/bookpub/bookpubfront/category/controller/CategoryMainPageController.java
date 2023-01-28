package com.nhnacademy.bookpub.bookpubfront.category.controller;

import com.nhnacademy.bookpub.bookpubfront.category.dto.response.GetParentCategoryWithChildrenResponseDto;
import com.nhnacademy.bookpub.bookpubfront.category.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 메인페이지에서 카테고리 view 로 이동하는 컨트롤러입니다.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class CategoryMainPageController {

    private final CategoryService categoryService;

    /**
     * 카테고리 페이지로 이동합니다.
     *
     * @param model view 로 정보를 보내는 request
     * @return 카테고리 페이지
     */
    @GetMapping("/categories")
    public String mainCategory(Model model) {
        List<GetParentCategoryWithChildrenResponseDto> parentCategoryWithChildren =
                categoryService.getParentCategoryWithChildren();
        model.addAttribute("category", parentCategoryWithChildren);

        return "main/categories";
    }

}
