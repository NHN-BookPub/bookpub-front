package com.nhnacademy.bookpub.bookpubfront.reviewpolicy.controller;

import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.request.CreateReviewPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.request.ModifyReviewPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.reviewpolicy.service.ReviewPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/review/review-policies")
public class AdminReviewPolicyController {
    private final ReviewPolicyService reviewPolicyService;

    @GetMapping
    public String reviewPolicyList(Model model) {
        model.addAttribute("reviewPolicyList", reviewPolicyService.getReviewPolicies());

        return "admin/review/reviewPolicyIndex";
    }

    @PostMapping
    public String reviewPolicyAdd(CreateReviewPolicyRequestDto request) {
        reviewPolicyService.createReviewPolicy(request);

        return "redirect:/admin/review/review-policies";
    }

    @PostMapping("/modify")
    public String reviewPolicyModify(@ModelAttribute ModifyReviewPolicyRequestDto request) {
        reviewPolicyService.modifyReviewPolicy(request);

        return "redirect:/admin/review/review-policies";
    }


    @PostMapping("/{policyNo}/used")
    public String reviewPolicyModifyUsed(@PathVariable Integer policyNo) {
        reviewPolicyService.modifyReviewPolicyUsed(policyNo);

        return "redirect:/admin/review/review-policies";
    }
}
