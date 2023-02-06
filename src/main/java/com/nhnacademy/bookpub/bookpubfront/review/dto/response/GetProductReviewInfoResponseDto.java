package com.nhnacademy.bookpub.bookpubfront.review.dto.response;

import lombok.Getter;

/**
 * 상품의 상품평 요약정보를 조회할 때 사용되는 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
public class GetProductReviewInfoResponseDto {
    private Long reviewCount;
    private Integer productStar;
}
