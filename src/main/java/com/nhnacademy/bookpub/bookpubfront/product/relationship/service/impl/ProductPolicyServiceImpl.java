package com.nhnacademy.bookpub.bookpubfront.product.relationship.service.impl;

import com.nhnacademy.bookpub.bookpubfront.product.relationship.adaptor.ProductPolicyAdaptor;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.request.CreateProductPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.request.ModifyProductPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.response.GetProductPolicyResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.service.ProductPolicyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 상품 정책 서비스 구현체.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class ProductPolicyServiceImpl implements ProductPolicyService {
    private final ProductPolicyAdaptor productPolicyAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetProductPolicyResponseDto> getProductPolicies() {
        return productPolicyAdaptor.getProductPolicies();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createProductPolicy(CreateProductPolicyRequestDto createRequestDto) {
        productPolicyAdaptor.createProductPolicy(createRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyProductPolicy(Integer policyNo,
                                    ModifyProductPolicyRequestDto modifyRequestDto) {
        productPolicyAdaptor.modifyProductPolicy(policyNo, modifyRequestDto);
    }
}
