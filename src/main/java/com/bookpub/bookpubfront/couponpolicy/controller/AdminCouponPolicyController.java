package com.bookpub.bookpubfront.couponpolicy.controller;

import com.bookpub.bookpubfront.couponpolicy.dto.request.CreateCouponPolicyRequestDto;
import com.bookpub.bookpubfront.couponpolicy.dto.request.ModifyCouponPolicyRequestDto;
import com.bookpub.bookpubfront.couponpolicy.service.CouponPolicyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 관리자 페이지의 쿠폰 정책을 관리하기 위한 컨트롤러입니다.
 *
 * @author : 정유진
 * @since : 1.0
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminCouponPolicyController {

    private final CouponPolicyService couponPolicyService;

    /**
     * 쿠폰정책 리스트를 조회하기 위한 정보를 담아 view로 가기 위한 메소드입니다.
     *
     * @param model 뷰에서 띄워줄 정보를 담은 request
     * @return 쿠폰 정책 리스트 조회 뷰
     * @throws JsonProcessingException json 관련 에러
     */
    @GetMapping("/coupon/coupon-policies")
    public String goCouponPolicy(Model model) throws JsonProcessingException {
        model.addAttribute("couponPolicyList", couponPolicyService.getCouponPolicies());

        return "admin/coupon/couponPolicyPage";
    }

    /**
     * 쿠폰정책을 등록하고, view로 가기 위한 메소드입니다.
     *
     * @param createRequestDto 쿠폰정책 등록에 필요한 정보를 담은 Dto
     * @return 쿠폰 정책 리스트 조회 뷰
     * @throws JsonProcessingException json 관련 에러
     */
    @PostMapping("/coupon/coupon-policies")
    public String addCouponPolicy(@ModelAttribute CreateCouponPolicyRequestDto createRequestDto)
            throws JsonProcessingException {
        couponPolicyService.createCouponPolicy(createRequestDto);

        return "redirect:/admin/coupon/coupon-policies";
    }

    /**
     * 쿠폰정책을 수정하고, view로 가기 위한 메소드입니다.
     *
     * @param modifyRequestDto 쿠폰정책 수정에 필요한 정보를 담은 Dto
     * @return 쿠폰 정책 리스트 조회 뷰
     * @throws JsonProcessingException json 관련 에러
     */
    @PostMapping("/coupon/coupon-policies/modify")
    public String modifyCouponPolicy(@ModelAttribute ModifyCouponPolicyRequestDto modifyRequestDto)
            throws JsonProcessingException {
        couponPolicyService.modifyCouponPolicy(modifyRequestDto);

        return "redirect:/admin/coupon/coupon-policies";
    }

}
