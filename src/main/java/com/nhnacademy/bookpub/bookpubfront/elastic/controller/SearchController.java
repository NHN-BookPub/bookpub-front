package com.nhnacademy.bookpub.bookpubfront.elastic.controller;

import com.nhnacademy.bookpub.bookpubfront.category.util.CategoryUtils;
import com.nhnacademy.bookpub.bookpubfront.elastic.dto.response.ProductSearchResultDto;
import com.nhnacademy.bookpub.bookpubfront.elastic.service.ElasticService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 검색을 처리할 controller.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class SearchController {

    private final ElasticService searchService;
    private final CategoryUtils categoryUtils;

    /**
     * 통합 검색 <Br/>
     * 상품 제목 검색 <br/>
     * 메인화면에서 검색을 처리하는 메서드.
     *
     * @param type    검색 코드(통합 or 제목)
     * @param keyword 검색어
     * @param model   model
     * @return 검색 결과 페이지
     */
    @GetMapping("/search")
    public String mainSearch(@RequestParam(name = "type") String type,
                             @RequestParam(name = "q") String keyword, Model model) {

        List<ProductSearchResultDto> products = searchService.searchProduct(keyword);
        model.addAttribute("products", products);

        categoryUtils.categoriesView(model);
        model.addAttribute("keyword", keyword);
        model.addAttribute("type", type);

        return "main/mainSearch";
    }
}
