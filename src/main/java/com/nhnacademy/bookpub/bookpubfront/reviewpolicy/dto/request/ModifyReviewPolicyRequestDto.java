package com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 상품평 정책 수정을 위한 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class ModifyReviewPolicyRequestDto {
    private Integer policyNo;
    private Long sendPoint;
}
