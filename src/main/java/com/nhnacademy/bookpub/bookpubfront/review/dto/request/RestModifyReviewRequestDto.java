package com.nhnacademy.bookpub.bookpubfront.review.dto.request;

import lombok.Builder;
import lombok.Getter;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
public class RestModifyReviewRequestDto {
    private Long reviewStar;
    private String reviewContent;

    @Builder
    public RestModifyReviewRequestDto(ModifyReviewRequestDto request) {
        this.reviewStar = request.getReviewStar();
        this.reviewContent = request.getReviewContent();
    }
}
