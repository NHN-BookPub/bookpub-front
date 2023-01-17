package com.bookpub.bookpubfront.product.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 상품 목록 페이지에서 보여줄 정보를 담는 DTO.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class GetProductListResponseDto {
    private Long productNo;
    private String title;
    private Integer productStock;
    private Long salesPrice;
    private Integer saleRate;
    private Long productPrice;
    private boolean deleted;
}

