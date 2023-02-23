package com.nhnacademy.bookpub.bookpubfront.sales.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 상품 판매량 랭킹 조회시 사용되는 dto.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class SaleProductCntDto {
    private String productTitle;
    private Long productCount;
}
