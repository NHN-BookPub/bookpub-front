package com.nhnacademy.bookpub.bookpubfront.review.controller;

import com.nhnacademy.bookpub.bookpubfront.member.service.MemberService;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductSimpleResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.request.CreateReviewRequestDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.request.ModifyReviewRequestDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.response.GetMemberReviewResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.service.ReviewService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final MemberService memberService;

    @GetMapping("/members/my/written-reviews")
    public String myWrittenReviewList(Model model, Pageable pageable) {
        Long memberNo = Long.parseLong((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        PageResponse<GetMemberReviewResponseDto> writtenReviewList =
                reviewService.getMemberReviews(memberNo, pageable);

        model.addAttribute("member", memberService.getMember(memberNo));
        model.addAttribute("myWrittenReviewList", writtenReviewList.getContent());
        model.addAttribute("totalPages", writtenReviewList.getTotalPages());
        model.addAttribute("currentPage", writtenReviewList.getNumber());
        model.addAttribute("isNext", writtenReviewList.isNext());
        model.addAttribute("isPrevious", writtenReviewList.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "mypage/myPageReview";
    }

    @GetMapping("/members/my/writable-reviews")
    public String myWritableReviewList(Model model, Pageable pageable) {
        Long memberNo = Long.parseLong((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        PageResponse<GetProductSimpleResponseDto> writableReviewList =
                reviewService.getMemberWritableReviews(memberNo, pageable);

        model.addAttribute("member", memberService.getMember(memberNo));
        model.addAttribute("myWritableReviewList", writableReviewList.getContent());
        model.addAttribute("totalPages", writableReviewList.getTotalPages());
        model.addAttribute("currentPage", writableReviewList.getNumber());
        model.addAttribute("isNext", writableReviewList.isNext());
        model.addAttribute("isPrevious", writableReviewList.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "mypage/myPageWritableReview";
    }

    @PostMapping("/members/my/writable-reviews")
    public String reviewAdd(CreateReviewRequestDto request) {
        reviewService.createReview(request);

        return "redirect:/members/my/writable-reviews";
    }

    @PostMapping("/members/my/written-reviews/{reviewNo}")
    public String reviewDelete(@PathVariable("reviewNo") Long reviewNo) {
        reviewService.deleteReview(reviewNo);

        return "redirect:/members/my/written-reviews";
    }

    @PostMapping("/members/my/written-reviews/{reviewNo}/modify")
    public String reviewModify(@PathVariable("reviewNo") Long reviewNo,
                               ModifyReviewRequestDto request) {
        reviewService.modifyReview(reviewNo, request);

        return "redirect:/members/my/written-reviews";
    }
}
