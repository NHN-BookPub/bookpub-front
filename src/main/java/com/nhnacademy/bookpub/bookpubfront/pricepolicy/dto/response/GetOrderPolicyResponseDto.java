package com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 오더 view를 구성하는데 필요한 정책들을 반환받은 dto입니다.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderPolicyResponseDto {
    private Integer policyNo;
    private String policyName;
    private Long policyFee;
}
