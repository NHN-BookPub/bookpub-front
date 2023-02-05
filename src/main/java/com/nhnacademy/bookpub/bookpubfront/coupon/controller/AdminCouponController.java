package com.nhnacademy.bookpub.bookpubfront.coupon.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.coupon.dto.request.CreateCouponRequestDto;
import com.nhnacademy.bookpub.bookpubfront.coupon.dto.response.GetCouponResponseDto;
import com.nhnacademy.bookpub.bookpubfront.coupon.service.CouponService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 관리자 페이지의 쿠폰을 관리하기 위한 컨트롤러입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminCouponController {
    private final CouponService couponService;

    /**
     * 쿠폰 리스트를 보여주는 뷰로 이동하기 위한 메소드입니다.
     *
     * @param searchKey 검색 조건
     * @param search    검색어
     * @param pageable  페이지 정보
     * @param model     the model
     * @return 쿠폰 리스트 조회 뷰
     */
    @Auth
    @GetMapping("/coupons")
    public String couponList(@RequestParam(value = "searchKey", required = false) String searchKey,
                             @RequestParam(value = "search", required = false) String search,
                             @PageableDefault Pageable pageable, Model model) {
        PageResponse<GetCouponResponseDto> pageCoupons = couponService.getCoupons(pageable, searchKey, search);
        List<GetCouponResponseDto> couponList = pageCoupons.getContent();

        model.addAttribute("couponList", couponList);
        model.addAttribute("totalPages", pageCoupons.getTotalPages());
        model.addAttribute("currentPage", pageCoupons.getNumber());
        model.addAttribute("isNext", pageCoupons.isNext());
        model.addAttribute("isPrevious", pageCoupons.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "admin/coupon/couponPage";
    }

    /**
     * 쿠폰을 등록하기 위한 메소드입니다.
     *
     * @param createRequestDto 쿠폰 등록시 필요한 정보를 담은 dto
     * @return 쿠폰 리스트 조회 뷰로 redirect
     */
    @Auth
    @PostMapping("/coupons")
    public String addCoupon(CreateCouponRequestDto createRequestDto) {
        couponService.createCoupon(createRequestDto);

        return "redirect:/admin/coupons";
    }
}
