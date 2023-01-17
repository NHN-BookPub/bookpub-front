package com.bookpub.bookpubfront.product.relationship.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 상품 판매 상태 코드 응답을 받는 DTO.
 *
 * @author : 박경서, 정유진, 김서현
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class GetProductSaleStateCodeResponseDto {
    private Integer codeNo;
    private String codeCategory;
}
