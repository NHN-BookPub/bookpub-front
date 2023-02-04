package com.nhnacademy.bookpub.bookpubfront.review.controller;

import com.nhnacademy.bookpub.bookpubfront.review.dto.response.GetMemberReviewResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.response.GetProductReviewResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.service.ReviewService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@RestController
@RequiredArgsConstructor
public class ReviewRestController {
    private final ReviewService reviewService;

    @GetMapping("/members/my/written-reviews/{reviewNo}")
    public GetMemberReviewResponseDto myWrittenReviewDetail(@PathVariable Long reviewNo) {
        return reviewService.getReview(reviewNo);
    }

    @PutMapping("/members/my/written-reviews/{reviewNo}/file")
    public void deleteReviewImage(@PathVariable Long reviewNo) {
        reviewService.deleteReviewImage(reviewNo);
    }

    @GetMapping("/reviews/product/{productNo}")
    public PageResponse<GetProductReviewResponseDto> getProductReviews(@PathVariable Long productNo,
                                                                       @RequestParam("page") Integer page,
                                                                       @RequestParam("size") Integer size) {
        return reviewService.getProductReviews(productNo, PageRequest.of(page, size));
    }
}
