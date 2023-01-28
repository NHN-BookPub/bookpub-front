package com.nhnacademy.bookpub.bookpubfront.product.relationship.service.impl;

import com.nhnacademy.bookpub.bookpubfront.product.relationship.adaptor.ProductSaleStateCodeAdaptor;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.response.GetProductSaleStateCodeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.service.ProductSaleStateCodeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 상품 판매 상태 코드 서비스 구현체.
 *
 * @author : 박경서, 정유진, 김서현
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class ProductSaleStateCodeServiceImpl implements ProductSaleStateCodeService {
    private final ProductSaleStateCodeAdaptor productSaleStateCodeAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetProductSaleStateCodeResponseDto> getProductSaleStateCodes() {
        return productSaleStateCodeAdaptor.requestProductSaleStateCodes();
    }
}
