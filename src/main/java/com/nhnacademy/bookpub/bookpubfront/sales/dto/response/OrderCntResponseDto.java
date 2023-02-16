package com.nhnacademy.bookpub.bookpubfront.sales.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 시간당 주문수를 측정하기위한 클래스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCntResponseDto {
    private Integer date;
    private Long orderCnt;
}
