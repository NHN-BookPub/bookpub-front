package com.nhnacademy.bookpub.bookpubfront.category.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.bookpub.bookpubfront.category.dto.request.CreateCategoryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.category.dto.request.ModifyCategoryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.category.dto.response.GetCategoryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.category.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 관리자 페이지에서 카테고리 관리를 위한 컨트롤러.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 관리자가 카테고리 메인 페이지(카테고리 리스트 조회)로 갈 수 있도록 한다.
     *
     * @param model view 로 정보를 보내는 request
     * @return 카테고리 메인 페이지
     */
    @GetMapping
    public String categoryList(Model model) {
        List<GetCategoryResponseDto> categories = categoryService.getCategories();
        List<GetCategoryResponseDto> parentCategories = categoryService.getParentCategories();
        model.addAttribute("parentCategories", parentCategories);
        model.addAttribute("categories", categories);
        return "admin/category/categoryIndex";
    }

    /**
     * 관리자가 카테고리 등록을 처리하는 메소드.
     *
     * @param requestDto 등록 폼에서 입력된 값을 받는 dto.
     * @return 카테고리 메인(카테고리 리스트)로 redirect
     * @throws JsonProcessingException objectMapper 발생시키는에러.
     */
    @PostMapping
    public String addCategory(@ModelAttribute CreateCategoryRequestDto requestDto)
            throws JsonProcessingException {
        categoryService.createCategory(requestDto);

        return "redirect:/admin/categories";
    }

    /**
     * 관리자가 카테고리 수정을 처리하는 메소드.
     *
     * @param requestDto 수정폼에서 입력된 값을 받는 dto.
     * @return 카테고리 메인(카테고리 리스트)로 redirect
     * @throws JsonProcessingException objectMapper 발생시키는에러.
     */
    @PostMapping("/modify")
    public String modifyCategory(@ModelAttribute ModifyCategoryRequestDto requestDto)
            throws JsonProcessingException {
        categoryService.modifyCategory(requestDto);

        return "redirect:/admin/categories";
    }

}
