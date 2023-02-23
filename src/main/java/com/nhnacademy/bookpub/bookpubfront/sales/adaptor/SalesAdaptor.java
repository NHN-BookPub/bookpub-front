package com.nhnacademy.bookpub.bookpubfront.sales.adaptor;

import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.OrderCntResponseDto;
import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.SaleProductCntDto;
import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.TotalSaleDto;
import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.TotalSaleYearDto;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 매출관련 통신을 하기위한 어뎁터입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
public interface SalesAdaptor {
    /**
     * 매출정보를 얻기위한 요청입니다.
     *
     * @param start 시작일자
     * @param end   종료일자
     * @return 매출값 반환.
     */
    List<TotalSaleDto> getSalesRequest(LocalDateTime start, LocalDateTime end);

    /**
     * 주문정보를 얻기위한 요청입니다.
     *
     * @return 주문카운트 정보 반환.
     */
    List<OrderCntResponseDto> orderCntRequest();

    /**
     * 월별 매출정보를 얻어오기위한 요청입니다.
     *
     * @param start 시작일자
     * @param end   종료일자
     * @return 월별정보 넣기.
     */
    List<TotalSaleYearDto> getSalesYearRequest(LocalDateTime start,
                                               LocalDateTime end);

    /**
     * 상품 판매량 랭킹을 조회하기 위한 요청입니다.
     *
     * @param start 시작일자
     * @param end   종료일자
     * @return 상품 판매량 랭킹 정보가 담긴 dto 리스트
     */
    List<SaleProductCntDto> requestSaleProductRankCount(LocalDateTime start, LocalDateTime end);
}
