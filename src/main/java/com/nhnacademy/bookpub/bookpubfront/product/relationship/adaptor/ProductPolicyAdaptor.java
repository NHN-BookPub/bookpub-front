package com.nhnacademy.bookpub.bookpubfront.product.relationship.adaptor;

import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.request.CreateProductPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.request.ModifyProductPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.response.GetProductPolicyResponseDto;
import java.util.List;

/**
 * API 서버와 통신을 담당하는 상품 정책 어댑터.
 *
 * @author : 박경서, 정유진, 김서현
 * @since : 1.0
 **/
public interface ProductPolicyAdaptor {

    /**
     * 전체 상품 정책 API 호출하는 메서드.
     *
     * @return 상품 정책 응답 DTO
     */
    List<GetProductPolicyResponseDto> getProductPolicies();

    /**
     * 상품 등록 API 호출하는 메서드.
     *
     * @param createRequestDto 상품 등록시 필요한 정보를 담은 DTO
     */
    void createProductPolicy(CreateProductPolicyRequestDto createRequestDto);

    /**
     * 상품 수정 API 호출하는 메서드.
     *
     * @param policyNo         상품 번호
     * @param modifyRequestDto 상품 수정시 필요한 정보를 담은 DTO
     */
    void modifyProductPolicy(Integer policyNo, ModifyProductPolicyRequestDto modifyRequestDto);
}
