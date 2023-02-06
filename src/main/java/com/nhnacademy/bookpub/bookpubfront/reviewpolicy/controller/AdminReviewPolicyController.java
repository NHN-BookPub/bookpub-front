package com.nhnacademy.bookpub.bookpubfront.reviewpolicy.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
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
 * 상품평 정책을 다루기 위한 컨트롤러입니다.
 * 관리자 페이지 뷰로 이동합니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/review/review-policies")
public class AdminReviewPolicyController {
    private final ReviewPolicyService reviewPolicyService;

    /**
     * 상품평 정책 리스트 조회를 위한 메서드입니다.
     * 관리자 페이지로 이동합니다.
     *
     * @param model the model
     * @return 관리자 상품평 정책 조회 페이지
     */
    @Auth
    @GetMapping
    public String reviewPolicyList(Model model) {
        model.addAttribute("reviewPolicyList", reviewPolicyService.getReviewPolicies());

        return "admin/review/reviewPolicyIndex";
    }

    /**
     * 상품평 정책 등록을 위한 메서드입니다.
     * 관리자를 위한 메서드이며, 등록 완료 후 관리자 페이지로 이동합니다.
     *
     * @param request the request
     * @return 관리자 상품평 정책 조회 페이지
     */
    @Auth
    @PostMapping
    public String reviewPolicyAdd(CreateReviewPolicyRequestDto request) {
        reviewPolicyService.createReviewPolicy(request);

        return "redirect:/admin/review/review-policies";
    }

    /**
     * 상품평 정책 수정을 위한 메서드입니다.
     * 관리자를 위한 메서드이며, 수정 완료 후 관리자 페이지로 이동합니다.
     *
     * @param request the request
     * @return 관리자 상품평 정책 조회 페이지
     */
    @Auth
    @PostMapping("/modify")
    public String reviewPolicyModify(@ModelAttribute ModifyReviewPolicyRequestDto request) {
        reviewPolicyService.modifyReviewPolicy(request);

        return "redirect:/admin/review/review-policies";
    }

    /**
     * 상품평 정책 현재 사용여부 수정을 위한 메서드입니다.
     * 관리자를 위한 메서드이며, 수정 완료 후 관리자 페이지로 이동합니다.
     *
     * @param policyNo 수정할 상품평 정책 번호
     * @return 관리자 상품평 정책 조회 페이지
     */
    @Auth
    @PostMapping("/{policyNo}/used")
    public String reviewPolicyModifyUsed(@PathVariable Integer policyNo) {
        reviewPolicyService.modifyReviewPolicyUsed(policyNo);

        return "redirect:/admin/review/review-policies";
    }
}
