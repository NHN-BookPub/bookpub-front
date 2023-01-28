package com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 가격정책을 반환하기 위한 Dto.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class GetPricePolicyResponseDto {
    private Integer pricePolicyNo;
    private String policyName;
    private Long policyFee;
}
