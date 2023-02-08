package com.nhnacademy.bookpub.bookpubfront.reviewpolicy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 상품평 정책 등록을 위한 Dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreateReviewPolicyRequestDto {
    private Long sendPoint;
}
