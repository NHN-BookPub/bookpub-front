package com.nhnacademy.bookpub.bookpubfront.wishlist.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.category.dto.response.GetCategoryInfoResponseDto;
import com.nhnacademy.bookpub.bookpubfront.category.service.CategoryService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.response.GetWishlistCountResponseDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.service.WishlistService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 관리자 페이지에서 좋아요 현황 리스트.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class AdminWishListController {

    private final WishlistService wishlistService;

    private final CategoryService categoryService;


    /**
     * 관리자 페이지에서 각 상품 좋아요 카운트 조회. ( 카테고리 번호가 존재하면 카테고리 별 조회)
     *
     * @param pageable 페이징 정보
     * @param categoryNo 카테고리 번호.
     * @param model model
     * @return 좋아요 현황 관리자 페이지
     */
    @Auth
    @GetMapping("/admin/wishlist")
    public String getWishListCount(@PageableDefault Pageable pageable,
            @RequestParam(value = "categoryNo", required = false) Integer categoryNo, Model model) {


        List<GetCategoryInfoResponseDto> allCategories = categoryService.getAllCategories();

        PageResponse<GetWishlistCountResponseDto> wishListCount = wishlistService.getWishListCount(
                categoryNo,
                pageable);

        List<GetWishlistCountResponseDto> content = wishListCount.getContent();

        model.addAttribute("categoryList", allCategories);

        model.addAttribute("wishListCounts", content);
        model.addAttribute("totalPages", wishListCount.getTotalPages());
        model.addAttribute("currentPage", wishListCount.getNumber());
        model.addAttribute("isNext", wishListCount.isNext());
        model.addAttribute("isPrevious", wishListCount.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "admin/wishlist/wishListIndex";
    }

}
