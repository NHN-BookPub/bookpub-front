package com.nhnacademy.bookpub.bookpubfront.sales.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Some description here.
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
