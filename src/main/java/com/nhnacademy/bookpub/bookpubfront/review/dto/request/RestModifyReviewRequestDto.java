package com.nhnacademy.bookpub.bookpubfront.review.dto.request;

import lombok.Builder;
import lombok.Getter;

/**
 * shop 서버와 통신을 위해 변환한 Dto입니다.
 * ModifyReviewRequestDto 에서 이미지가 빠진 Dto입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
public class RestModifyReviewRequestDto {
    private Integer reviewStar;
    private String reviewContent;

    /**
     * Dto 생성을 위한 빌더.
     *
     * @param request ModifyReviewRequestDto
     */
    @Builder
    public RestModifyReviewRequestDto(ModifyReviewRequestDto request) {
        this.reviewStar = request.getReviewStar();
        this.reviewContent = request.getReviewContent();
    }
}
