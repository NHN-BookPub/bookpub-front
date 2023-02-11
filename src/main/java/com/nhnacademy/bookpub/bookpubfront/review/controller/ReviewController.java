package com.nhnacademy.bookpub.bookpubfront.review.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.member.service.MemberService;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductSimpleResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.request.CreateReviewRequestDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.request.ModifyReviewRequestDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.response.GetMemberReviewResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.service.ReviewService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 상품평 뷰를 다루기 위한 컨트롤러입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final MemberService memberService;
    private final MemberUtils memberUtils;

    /**
     * 마이페이지에서 회원 본인이 작성한 상품평들을 보여주기 위한 메서드입니다.
     *
     * @param model    the model
     * @param pageable the pageable
     * @return 마이페이지의 작성한 상품평 조회 뷰
     */
    @Auth
    @GetMapping("/members/written-reviews")
    public String myWrittenReviewList(Model model, Pageable pageable) {
        Long memberNo = memberUtils.getMemberNo();
        memberUtils.modelRequestMemberNo(model);

        PageResponse<GetMemberReviewResponseDto> writtenReviewList =
                reviewService.getMemberReviews(memberNo, pageable);

        model.addAttribute("myWrittenReviewList", writtenReviewList.getContent());
        model.addAttribute("totalPages", writtenReviewList.getTotalPages());
        model.addAttribute("currentPage", writtenReviewList.getNumber());
        model.addAttribute("isNext", writtenReviewList.isNext());
        model.addAttribute("isPrevious", writtenReviewList.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "mypage/myPageReview";
    }

    /**
     * 마이페이지에서 회원 본인이 상품평 작성 가능한 상품들의 정보를 보여주기 위한 메서드입니다.
     *
     * @param model    the model
     * @param pageable the pageable
     * @return 마이페이지의 작성 가능한 상품평 조회 뷰
     */
    @Auth
    @GetMapping("/members/writable-reviews")
    public String myWritableReviewList(Model model, Pageable pageable) {
        Long memberNo = memberUtils.getMemberNo();
        memberUtils.modelRequestMemberNo(model);

        PageResponse<GetProductSimpleResponseDto> writableReviewList =
                reviewService.getMemberWritableReviews(memberNo, pageable);

        model.addAttribute("myWritableReviewList", writableReviewList.getContent());
        model.addAttribute("totalPages", writableReviewList.getTotalPages());
        model.addAttribute("currentPage", writableReviewList.getNumber());
        model.addAttribute("isNext", writableReviewList.isNext());
        model.addAttribute("isPrevious", writableReviewList.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "mypage/myPageWritableReview";
    }

    /**
     * 상품평 등록을 위한 메서드입니다.
     * 등록 후, 마이페이지의 작성 가능한 상품평 조회 뷰로 이동합니다.
     *
     * @param request 등록할 상품평 정보를 담은 Dto
     * @return the string
     */
    @Auth
    @PostMapping("/members/writable-reviews")
    public String reviewAdd(CreateReviewRequestDto request) {
        reviewService.createReview(request);

        return "redirect:/members/writable-reviews";
    }

    /**
     * 상품평 삭제를 위한 메서드입니다.
     * 삭제 후, 마이페이지의 작성한 상품평 조회 뷰로 이동합니다.
     *
     * @param reviewNo 삭제할 상품평 번호
     * @return 마이페이지의 작성한 상품평 조회 뷰
     */
    @Auth
    @PostMapping("/members/written-reviews/{reviewNo}")
    public String reviewDelete(@PathVariable("reviewNo") Long reviewNo) {
        reviewService.deleteReview(reviewNo);

        return "redirect:/members/written-reviews";
    }

    /**
     * 상품평 수정을 위한 메서드입니다.
     * 수정 후, 마이페이지의 작성한 상품평 조회 뷰로 이동합니다.
     *
     * @param reviewNo 수정할 상품평 번호
     * @param request  수정할 상품평 정보를 담은 Dto
     * @return 마이페이지의 작성한 상품평 조회 뷰
     */
    @Auth
    @PostMapping("/members/written-reviews/{reviewNo}/modify")
    public String reviewModify(@PathVariable("reviewNo") Long reviewNo,
                               ModifyReviewRequestDto request) {
        reviewService.modifyReview(reviewNo, request);

        return "redirect:/members/written-reviews";
    }
}
