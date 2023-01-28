package com.nhnacademy.bookpub.bookpubfront.product.relationship.adaptor;


import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.response.GetProductTypeStateCodeResponseDto;
import java.util.List;

/**
 * API 서버와 통신을 담당하는 상품 타입 코드 어댑터.
 *
 * @author : 박경서, 정유진, 김서현
 * @since : 1.0
 **/
public interface ProductTypeStateCodeAdaptor {

    /**
     * 전체 상품 타입 코드 API 호출하는 메서드.
     *
     * @return 상품 타입 코드 DTO
     */
    List<GetProductTypeStateCodeResponseDto> requestProductTypeStateCodes();

}
