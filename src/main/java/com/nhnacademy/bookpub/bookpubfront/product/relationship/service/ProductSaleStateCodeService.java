package com.nhnacademy.bookpub.bookpubfront.product.relationship.service;

import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.response.GetProductSaleStateCodeResponseDto;
import java.util.List;

/**
 * 상품 판매 상태 코드를 서비스할 인터페이스.
 *
 * @author : 박경서, 정유진, 김서현
 * @since : 1.0
 **/
public interface ProductSaleStateCodeService {

    /**
     * 전체 상품 상태 코드를 받아오려는 메서드.
     *
     * @return 전체 상품 상태 코드 DTO
     */
    List<GetProductSaleStateCodeResponseDto> getProductSaleStateCodes();
}
