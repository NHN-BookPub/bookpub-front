package com.nhnacademy.bookpub.bookpubfront.tiercoupon.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.cart.util.CartUtils;
import com.nhnacademy.bookpub.bookpubfront.category.util.CategoryUtils;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import com.nhnacademy.bookpub.bookpubfront.tiercoupon.dto.request.CreateTierCouponRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tiercoupon.dto.response.GetTierCouponResponseDto;
import com.nhnacademy.bookpub.bookpubfront.tiercoupon.service.TierCouponService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 관리자 페이지의 등급 쿠폰을 관리하기 위한 컨트롤러입니다.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@RequiredArgsConstructor
@Controller
public class TierCouponController {

    private final TierCouponService tierCouponService;

    private final MemberUtils memberUtils;

    private final CartUtils cartUtils;

    private final CategoryUtils categoryUtils;

    private static final String CART = "CART";

    /**
     * 등급 쿠폰 목록 조회 메서드입니다.
     *
     * @param model    model
     * @param pageable 페이지 정보
     * @return 등급 쿠폰 관리자 페이지
     */
    @Auth
    @GetMapping("/admin/coupon/tier-coupons")
    public String tierCouponList(Model model, @PageableDefault Pageable pageable) {
        PageResponse<GetTierCouponResponseDto> tierCoupons = tierCouponService.getTierCoupons(
                pageable);
        List<GetTierCouponResponseDto> tierCouponList = tierCoupons.getContent();

        model.addAttribute("tierCouponList", tierCouponList);
        model.addAttribute("totalPages", tierCoupons.getTotalPages());
        model.addAttribute("currentPage", tierCoupons.getNumber());
        model.addAttribute("isNext", tierCoupons.isNext());
        model.addAttribute("isPrevious", tierCoupons.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "admin/coupon/tierCouponIndex";
    }

    /**
     * 등급 쿠폰 등록하는 메서드입니다.
     *
     * @param createRequestDto 등급 쿠폰 등록을 위한 정보
     * @return 관리자 등급 쿠폰 페이지
     */
    @Auth
    @PostMapping("/admin/coupon/tier-coupons")
    public String addTierCoupon(CreateTierCouponRequestDto createRequestDto) {
        tierCouponService.createTierCoupon(createRequestDto);

        return "redirect:/admin/coupon/tier-coupons";
    }


    /**
     * 등급 쿠폰 삭제 메서드입니다.
     *
     * @param tierNo     등급 번호
     * @param templateNo 쿠폰 템플릿 번호
     * @return 관리자 등급 쿠폰 페이지
     */
    @Auth
    @PostMapping("/admin/coupon/tier-coupons/delete")
    public String deleteTierCoupon(@RequestParam Integer tierNo, @RequestParam Long templateNo) {
        tierCouponService.deleteTierCoupon(tierNo, templateNo);

        return "redirect:/admin/coupon/tier-coupons";
    }

    /**
     * 등급 쿠폰 발급 페이지로 이동합니다.
     *
     * @return 등급 쿠폰 발급 페이지
     */
    @GetMapping("/tier-coupons")
    public String tierCouponEvent(@CookieValue(name = CART, required = false) Cookie cookie,
            Model model) {

        memberUtils.modelRequestMemberNo(model);

        cartUtils.getCountInCart(cookie.getValue(), model);
        categoryUtils.categoriesView(model);

        return "coupon/tierCoupon";
    }

}
