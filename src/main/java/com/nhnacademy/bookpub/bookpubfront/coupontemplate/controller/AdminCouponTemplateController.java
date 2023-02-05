package com.nhnacademy.bookpub.bookpubfront.coupontemplate.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.couponpolicy.dto.response.GetCouponPolicyResponseDto;
import com.nhnacademy.bookpub.bookpubfront.couponpolicy.service.CouponPolicyService;
import com.nhnacademy.bookpub.bookpubfront.couponstatecode.dto.response.GetCouponStateCodeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.couponstatecode.service.CouponStateCodeService;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.request.CreateCouponTemplateRequestDto;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.request.ModifyCouponTemplateRequestDto;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.response.GetCouponTemplateResponseDto;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.response.GetDetailCouponTemplateResponseDto;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.service.CouponTemplateService;
import com.nhnacademy.bookpub.bookpubfront.coupontype.dto.response.GetCouponTypeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.coupontype.service.CouponTypeService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 관리자 페이지의 쿠폰 템플릿을 관리하기 위한 컨트롤러입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminCouponTemplateController {
    private final CouponTemplateService couponTemplateService;
    private final CouponTypeService couponTypeService;
    private final CouponStateCodeService couponStateCodeService;
    private final CouponPolicyService couponPolicyService;

    /**
     * 쿠폰템플릿 페이지를 조회한 정보를 담아 view로 가기 위한 메소드입니다.
     *
     * @param pageable 페이지 정보
     * @param model    the model
     * @return 관리자 쿠폰템플릿 페이지
     */
    @Auth
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

        return "admin/coupon/couponTemplatePage";
    }

    /**
     * 쿠폰템플릿 단건 조회 및 view로 가기 위한 메소드입니다.
     *
     * @param templateNo 조회할 쿠폰템플릿 번호
     * @param model      the model
     * @return 쿠폰템플릿 상세보기 페이지
     */
    @Auth
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

    /**
     * 쿠폰템플릿을 등록하기 위한 메소드입니다.
     *
     * @param createRequestDto 등록할 쿠폰템플릿 정보를 담은 dto
     * @return 쿠폰템플릿 페이지로 이동
     * @throws IOException 파일로 인한 IOExcpeption
     */
    @Auth
    @PostMapping("/coupon/coupon-templates")
    public String addCouponTemplate(CreateCouponTemplateRequestDto createRequestDto) throws IOException {
        couponTemplateService.createCouponTemplate(createRequestDto);

        return "redirect:/admin/coupon/coupon-templates";
    }

    /**
     * 쿠폰템플릿을 수정하기 위한 메소드입니다.
     *
     * @param templateNo       수정할 쿠폰템플릿 번호
     * @param modifyRequestDto 수정할 쿠폰템플릿 정보를 담은 dto
     * @return 쿠폰템플릿 페이지로 이동
     */
    @Auth
    @PostMapping("/coupon/coupon-templates/modify/{templateNo}")
    public String modifyCouponTemplate(@PathVariable("templateNo") Long templateNo, ModifyCouponTemplateRequestDto modifyRequestDto) {
        couponTemplateService.modifyCouponTemplate(templateNo, modifyRequestDto);

        return "redirect:/admin/coupon/coupon-templates";
    }
}
