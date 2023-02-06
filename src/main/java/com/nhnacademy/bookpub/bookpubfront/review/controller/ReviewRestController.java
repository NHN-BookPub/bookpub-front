package com.nhnacademy.bookpub.bookpubfront.review.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
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
 * shop 서버와 상품평 정보 관련 통신을 위한 컨트롤러입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@RestController
@RequiredArgsConstructor
public class ReviewRestController {
    private final ReviewService reviewService;

    /**
     * 회원이 작성했던 상품평을 상세 조회하기 위한 메서드입니다.
     * 상품평 수정을 위해 사용됩니다.
     *
     * @param reviewNo 조회할 상품평 번호
     * @return 상품평 정보가 담긴 Dto
     */
    @Auth
    @GetMapping("/members/my/written-reviews/{reviewNo}")
    public GetMemberReviewResponseDto myWrittenReviewDetail(@PathVariable Long reviewNo) {
        return reviewService.getReview(reviewNo);
    }

    /**
     * 상품평 이미지 삭제를 위한 메서드입니다.
     * 상품평 수정을 위해 사용됩니다.
     *
     * @param reviewNo 이미지를 삭제할 상품평 번호
     */
    @Auth
    @PutMapping("/members/my/written-reviews/{reviewNo}/file")
    public void deleteReviewImage(@PathVariable Long reviewNo) {

        reviewService.deleteReviewImage(reviewNo);
    }

    /**
     * 상품의 리뷰들을 페이지형식으로 조회하기 위한 메서드입니다.
     * 비동기 통신으로 사용되는 메서드입니다.
     *
     * @param productNo 상품평을 조회할 상품 번호
     * @param page      페이지 번호
     * @param size      한 페이지 당 데이터 개수
     * @return 상품의 상품평 정보를 담은 Dto 페이지정보
     */
    @GetMapping("/reviews/product/{productNo}")
    public PageResponse<GetProductReviewResponseDto> getProductReviews(
            @PathVariable Long productNo,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size) {
        return reviewService.getProductReviews(productNo, PageRequest.of(page, size));
    }
}
