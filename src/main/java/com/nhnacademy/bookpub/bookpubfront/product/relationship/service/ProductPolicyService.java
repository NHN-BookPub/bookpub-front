package com.nhnacademy.bookpub.bookpubfront.product.relationship.service;

import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.request.CreateProductPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.request.ModifyProductPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.response.GetProductPolicyResponseDto;
import java.util.List;

/**
 * 상품 정책을 서비스할 인터페이스.
 *
 * @author : 박경서, 정유진, 김서현
 * @since : 1.0
 **/
public interface ProductPolicyService {

    /**
     * 전체 상품 정책을 받아오려는 메서드.
     *
     * @return 전체 상품 정책 응답 DTO
     */
    List<GetProductPolicyResponseDto> getProductPolicies();

    /**
     * 상품 등록을 처리하려는 메서드.
     *
     * @param createRequestDto 상품 등록 정보를 담은 DTO
     */
    void createProductPolicy(CreateProductPolicyRequestDto createRequestDto);

    /**
     * 상품 수정을 처리하는 메서드.
     *
     * @param policyNo         상품 번호
     * @param modifyRequestDto 상품 수정 정보를 담은 DTO
     */
    void modifyProductPolicy(Integer policyNo, ModifyProductPolicyRequestDto modifyRequestDto);
}
