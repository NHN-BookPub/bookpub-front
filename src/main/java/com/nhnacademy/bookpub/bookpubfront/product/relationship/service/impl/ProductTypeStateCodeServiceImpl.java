package com.nhnacademy.bookpub.bookpubfront.product.relationship.service.impl;

import com.nhnacademy.bookpub.bookpubfront.product.relationship.adaptor.ProductTypeStateCodeAdaptor;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.response.GetProductTypeStateCodeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.service.ProductTypeStateCodeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 상품 유형 상태 코드 서비스 구현체.
 *
 * @author : 박경서, 정유진, 김서현
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class ProductTypeStateCodeServiceImpl implements ProductTypeStateCodeService {

    private final ProductTypeStateCodeAdaptor productTypeStateCodeAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetProductTypeStateCodeResponseDto> getProductTypeStateCodes() {
        return productTypeStateCodeAdaptor.requestProductTypeStateCodes();
    }
}
