package com.nhnacademy.bookpub.bookpubfront.review.dto.request;

import lombok.Builder;
import lombok.Getter;

/**
 * shop 서버와 통신을 위해 변환한 Dto입니다.
 * CreateReviewRequestDto 에서 이미지가 빠지고, 멤버 번호가 추가됩니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
public class RestCreateReviewRequestDto {
    private Long productNo;
    private Long memberNo;
    private Integer reviewStar;
    private String reviewContent;

    /**
     * Dto 생성을 위한 빌더.
     *
     * @param request  CreateReviewRequestDto
     * @param memberNo 리뷰를 작성한 멤버 번호
     */
    @Builder
    public RestCreateReviewRequestDto(CreateReviewRequestDto request, Long memberNo) {
        this.productNo = request.getProductNo();
        this.memberNo = memberNo;
        this.reviewStar = request.getReviewStar();
        this.reviewContent = request.getReviewContent();
    }
}
