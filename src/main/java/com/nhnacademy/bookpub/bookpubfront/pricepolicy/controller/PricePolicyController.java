package com.nhnacademy.bookpub.bookpubfront.pricepolicy.controller;

import com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.CreatePricePolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.pricepolicy.service.PricePolicyService;
import com.nhnacademy.bookpub.bookpubfront.state.PricePolicyState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 가격정책 컨트롤러입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/pricepolicies")
public class PricePolicyController {
    private final PricePolicyService pricePolicyService;
    private static final String SHIPPING = "shipping";
    private static final String PACKAGING = "packaging";
    private static final String POLICY = "policies";

    /**
     * 가격정책 메인페이지 뷰를 보여줍니다.
     *
     * @param model 모델.
     * @return 가격정책 메인.
     */
    @GetMapping
    public String pricePolicyView(Model model) {
        model.addAttribute(POLICY, pricePolicyService.getPricePolicies());

        return "admin/pricepolicy/pricePolicyMain";
    }

    /**
     * policy 이름에 따른 리스트를 보여줍니다.
     *
     * @param model 모델.
     * @param policyName 정책 이름.
     * @return 정책 리스트 뷰
     */
    @GetMapping("/{policyName}")
    public String individualPolicyView(Model model, @PathVariable String policyName) {
        if(policyName.equals(SHIPPING)) {
            model.addAttribute(POLICY, pricePolicyService.getPricePoliciesByName(PricePolicyState.SHIPPING.getName()));
        }
        if(policyName.equals(PACKAGING)) {
            model.addAttribute(POLICY, pricePolicyService.getPricePoliciesByName(PricePolicyState.PACKAGING.getName()));
        }

        return "admin/pricepolicy/pricePolicyMain";
    }

    /**
     * 가격정책을 추가합니다.
     *
     * @param pricePolicyDto 추가를 위한 dto.
     * @return 가격정책 메인페이지로 돌아갑니다.
     */
    @PostMapping
    public String addPricePolicy(@ModelAttribute CreatePricePolicyRequestDto pricePolicyDto) {
        log.info("dto = {}", pricePolicyDto);
        pricePolicyService.createPricePolicy(pricePolicyDto);

        return "redirect:/admin/pricepolicies";
    }

    /**
     * 가격정책을 더해주는 뷰로 이동합니다.
     *
     * @return 추가등록 뷰.
     */
    @GetMapping("/add")
    public String addPricePolicyView() {
        return "admin/pricepolicy/pricePolicyAdd";
    }
}
