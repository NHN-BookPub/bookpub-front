package com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.response;

import lombok.Getter;

/**
 * Some description here.
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
