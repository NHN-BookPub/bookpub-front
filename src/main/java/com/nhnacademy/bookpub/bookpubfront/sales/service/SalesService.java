package com.nhnacademy.bookpub.bookpubfront.sales.service;

import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.OrderCntResponseDto;
import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.TotalSaleDto;
import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.TotalSaleYearDto;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 매출관련 서비스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
public interface SalesService {
    /**
     * 매출정보를 불러옵니다.
     *
     * @param start 시작일자
     * @param end   종료일자
     * @return 매출정보 반환.
     */
    List<TotalSaleDto> getSales(LocalDateTime start, LocalDateTime end);

    /**
     * 주문 수를 불러옵니다.
     *
     * @return 시간별 주문 수 반환.
     */
    List<OrderCntResponseDto> getOrderCnt();

    List<TotalSaleYearDto> getOrderYear(LocalDateTime start,
                                        LocalDateTime end);
}
