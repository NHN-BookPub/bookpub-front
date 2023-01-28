package com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.response;

import lombok.Getter;

/**
 * 상품 정책 응답을 받는 DTO.
 *
 * @author : 박경서, 정유진, 김서현
 * @since : 1.0
 **/
@Getter
public class GetProductPolicyResponseDto {
    private Integer policyNo;
    private String policyMethod;
    private boolean policySaved;
    private Integer saveRate;
}

