package com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.response;

import lombok.Getter;

/**
 * 상품평 정책 조회를 위한 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
public class GetReviewPolicyResponseDto {
    private Integer policyNo;
    private Long sendPoint;
    private boolean policyUsed;
}
