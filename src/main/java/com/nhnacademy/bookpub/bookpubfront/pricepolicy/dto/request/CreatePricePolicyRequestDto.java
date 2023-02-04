package com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.request;

import com.nhnacademy.bookpub.bookpubfront.state.PricePolicyState;
import com.nhnacademy.bookpub.bookpubfront.state.anno.StateCode;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 가격 정책 생성 dto.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
@ToString
public class CreatePricePolicyRequestDto {
    @StateCode(enumClass = PricePolicyState.class)
    private String policyName;
    @NotNull
    private Long policyFee;
}