package com.bookpub.bookpubfront.coupontemplate.controller;

import com.bookpub.bookpubfront.couponpolicy.dto.response.GetCouponPolicyResponseDto;
import com.bookpub.bookpubfront.couponpolicy.service.CouponPolicyService;
import com.bookpub.bookpubfront.couponstatecode.dto.response.GetCouponStateCodeResponseDto;
import com.bookpub.bookpubfront.couponstatecode.service.CouponStateCodeService;
import com.bookpub.bookpubfront.coupontemplate.dto.request.CreateCouponTemplateRequestDto;
import com.bookpub.bookpubfront.coupontemplate.dto.request.ModifyCouponTemplateRequestDto;
import com.bookpub.bookpubfront.coupontemplate.dto.response.GetCouponTemplateResponseDto;
import com.bookpub.bookpubfront.coupontemplate.dto.response.GetDetailCouponTemplateResponseDto;
import com.bookpub.bookpubfront.coupontemplate.service.CouponTemplateService;
import com.bookpub.bookpubfront.coupontype.dto.response.GetCouponTypeResponseDto;
import com.bookpub.bookpubfront.coupontype.service.CouponTypeService;
import com.bookpub.bookpubfront.utils.PageResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminCouponTemplateController {
    private final CouponTemplateService couponTemplateService;
    private final CouponTypeService couponTypeService;
    private final CouponStateCodeService couponStateCodeService;
    private final CouponPolicyService couponPolicyService;

    @GetMapping("/coupon/coupon-templates")
    public String couponTemplateList(@PageableDefault Pageable pageable, Model model) {
        PageResponse<GetCouponTemplateResponseDto> couponTemplates = couponTemplateService.getCouponTemplates(pageable);
        List<GetCouponTypeResponseDto> couponTypeList = couponTypeService.getCouponTypes();
        List<GetCouponStateCodeResponseDto> couponStateCodeList = couponStateCodeService.getCouponStateCodes();
        List<GetCouponPolicyResponseDto> couponPolicyList = couponPolicyService.getCouponPolicies();

        model.addAttribute("couponTypeList", couponTypeList);
        model.addAttribute("couponStateCodeList", couponStateCodeList);
        model.addAttribute("couponPolicyList", couponPolicyList);
        model.addAttribute("couponTemplateList", couponTemplates.getContent());
        model.addAttribute("totalPages", couponTemplates.getTotalPages());
        model.addAttribute("currentPage", couponTemplates.getNumber());
        model.addAttribute("isNext", couponTemplates.isNext());
        model.addAttribute("isPrevious", couponTemplates.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        log.info("content = {}", couponTemplates.getContent());


        return "admin/coupon/couponTemplatePage";
    }

    @GetMapping("/coupon/coupon-templates/{templateNo}")
    public String couponTemplateDetail(@PathVariable Long templateNo, Model model) {
        GetDetailCouponTemplateResponseDto couponTemplate =
                couponTemplateService.getDetailCouponTemplate(templateNo);
        List<GetCouponTypeResponseDto> couponTypeList = couponTypeService.getCouponTypes();
        List<GetCouponStateCodeResponseDto> couponStateCodeList = couponStateCodeService.getCouponStateCodes();
        List<GetCouponPolicyResponseDto> couponPolicyList = couponPolicyService.getCouponPolicies();


        model.addAttribute("couponTemplate", couponTemplate);
        model.addAttribute("couponTypeList", couponTypeList);
        model.addAttribute("couponStateCodeList", couponStateCodeList);
        model.addAttribute("couponPolicyList", couponPolicyList);


        return "admin/coupon/couponTemplateDetail";
    }

    @PostMapping("/coupon/coupon-templates")
    public String addCouponTemplate(CreateCouponTemplateRequestDto createRequestDto) throws IOException {
        couponTemplateService.createCouponTemplate(createRequestDto);

        return "redirect:/admin/coupon/coupon-templates";
    }

    @PostMapping("/coupon/coupon-templates/modify/{templateNo}")
    public String modifyCouponTemplate(@PathVariable("templateNo") Long templateNo, ModifyCouponTemplateRequestDto modifyRequestDto) {
        couponTemplateService.modifyCouponTemplate(templateNo, modifyRequestDto);

        return "redirect:/admin/coupon/coupon-templates";
    }
}
