package com.bookpub.bookpubfront.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 주문조회시 상품정보를 같이 주기 위한 Dto.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class GetProductListForOrderResponseDto {
    private Long productNo;
    private String title;
    private Long salesPrice;
    private Integer productAmount;
}
