package com.nhnacademy.bookpub.bookpubfront.product.dto.response;

import lombok.Getter;

/**
 * 상품 목록 페이지에서 보여줄 정보를 담는 DTO.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
public class GetProductListResponseDto {
    private Long productNo;
    private String title;
    private String thumbnail;
    private Integer productStock;
    private Long salesPrice;
    private Integer saleRate;
    private boolean productSubscribed;
    private Long productPrice;
    private boolean deleted;
}

