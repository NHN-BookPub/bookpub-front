package com.nhnacademy.bookpub.bookpubfront.pricepolicy.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.request.CreatePricePolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.pricepolicy.service.PricePolicyService;
import com.nhnacademy.bookpub.bookpubfront.state.PricePolicyState;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * 가격정책 메인페이지 뷰를 보여줍니다.
     *
     * @param model 모델.
     * @return 가격정책 메인.
     */
    @GetMapping
    public String pricePolicyView(Model model) {
        model.addAttribute("policies", pricePolicyService.getPricePolicies());

        return "admin/pricepolicies/pricePolicyMain";
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
        model.addAttribute("policies", pricePolicyService.getPricePoliciesByName(
                Arrays.stream(PricePolicyState.values())
                        .filter(policy -> policy.name().equals(policyName.toUpperCase()))
                        .findFirst()
                        .orElseThrow()
                        .getName()));

        return "admin/pricepolicies/pricePolicyMain";
    }

    /**
     * 가격정책을 추가합니다.
     *
     * @param pricePolicyDto 추가를 위한 dto.
     * @return 가격정책 메인페이지로 돌아갑니다.
     */
    @PostMapping
    @Auth
    public String addPricePolicy(@ModelAttribute CreatePricePolicyRequestDto pricePolicyDto) {
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
        return "admin/pricepolicies/pricePolicyAdd";
    }
}
