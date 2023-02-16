package com.nhnacademy.bookpub.bookpubfront.sales.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 매출관련 DTO 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class TotalSaleDto {
    private Integer cancelPaymentCnt;
    private Long cancelPaymentAmount;
    private Integer cancelOrderCnt;
    private Integer saleCnt;
    private Long saleAmount;
    private Long total;
}
