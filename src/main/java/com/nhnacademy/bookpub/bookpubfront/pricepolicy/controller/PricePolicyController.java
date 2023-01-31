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

    @GetMapping
    public String pricePolicyView(Model model) {
        model.addAttribute(POLICY, pricePolicyService.getPricePolicies());

        return "admin/pricepolicy/pricePolicyMain";
    }

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

    @PostMapping
    public String addPricePolicy(@ModelAttribute CreatePricePolicyRequestDto pricePolicyDto) {
        log.info("dto = {}", pricePolicyDto);
        pricePolicyService.createPricePolicy(pricePolicyDto);

        return "redirect:/admin/pricepolicies";
    }

    @GetMapping("/add")
    public String addPricePolicyView() {
        return "admin/pricepolicy/pricePolicyAdd";
    }
}
