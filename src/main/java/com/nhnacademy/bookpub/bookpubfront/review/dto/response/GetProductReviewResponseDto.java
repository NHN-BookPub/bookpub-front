package com.nhnacademy.bookpub.bookpubfront.review.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;

/**
 * 상품의 상품평들을 조회할 때 사용되는 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
public class GetProductReviewResponseDto {
    private Long reviewNo;
    private String memberNickname;
    private Integer reviewStar;
    private String reviewContent;
    private String imagePath;
    private LocalDateTime createdAt;
}
