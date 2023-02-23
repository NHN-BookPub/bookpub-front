package com.nhnacademy.bookpub.bookpubfront.sales.service.impl;

import com.nhnacademy.bookpub.bookpubfront.sales.adaptor.SalesAdaptor;
import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.OrderCntResponseDto;
import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.SaleProductCntDto;
import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.TotalSaleDto;
import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.TotalSaleYearDto;
import com.nhnacademy.bookpub.bookpubfront.sales.service.SalesService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 매출서비스 실 구현체입니다.
 *
 * @author : 유호철
 * @since : 1.0
 */
@Service
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService {
    private final SalesAdaptor salesAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TotalSaleDto> getSales(LocalDateTime start,
                                       LocalDateTime end) {
        if (Objects.isNull(start) || Objects.isNull(end)) {
            start = LocalDateTime.now().minusMonths(1);
            end = LocalDateTime.now();
        }
        return salesAdaptor.getSalesRequest(start, end);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderCntResponseDto> getOrderCnt() {
        List<OrderCntResponseDto> count = salesAdaptor.orderCntRequest();
        HashMap<Integer, Long> orderCnt = new HashMap<>();

        checkOrderCnt(count, orderCnt);
        count = new ArrayList<>();
        checkHours(count, orderCnt);
        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TotalSaleYearDto> getOrderYear(LocalDateTime start,
                                               LocalDateTime end) {
        List<TotalSaleYearDto> salesYearRequest = salesAdaptor.getSalesYearRequest(start, end);
        HashMap<Integer, TotalSaleYearDto> hashMap = new HashMap<>();

        checkSalesYear(salesYearRequest, hashMap);
        salesYearRequest = new ArrayList<>();
        checkMonth(salesYearRequest, hashMap);

        return salesYearRequest;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SaleProductCntDto> getSaleProductRankCount(LocalDateTime start, LocalDateTime end) {
        return salesAdaptor.requestSaleProductRankCount(start, end);
    }

    /**
     * 주문개수를 확인하기위해 map 으로 변환합니다.
     *
     * @param count    카운트 개수
     * @param orderCnt 주문 카운트
     */
    private static void checkOrderCnt(List<OrderCntResponseDto> count,
                                      HashMap<Integer, Long> orderCnt) {
        for (OrderCntResponseDto result : count) {
            orderCnt.put(result.getDate(), result.getOrderCnt());
        }
    }

    /**
     * 월별 주문조회.
     *
     * @param content 월별 조회.
     * @param hashMap 해쉬 맵.
     */
    private static void checkSalesYear(List<TotalSaleYearDto> content,
                                       HashMap<Integer, TotalSaleYearDto> hashMap) {
        for (TotalSaleYearDto totalSaleYearDto : content) {
            hashMap.put(totalSaleYearDto.getMonth(), totalSaleYearDto);
        }
    }

    private static void checkMonth(List<TotalSaleYearDto> content,
                                   HashMap<Integer, TotalSaleYearDto> hashMap) {
        for (int i = 1; i <= 12; i++) {
            content.add(hashMap.getOrDefault(i, new TotalSaleYearDto(null,
                    null, null, null, null, null, i)));
        }
    }

    /**
     * 24시간을 기준으로 계산하여 값으로 변환합니다.
     *
     * @param count    카운트 개수
     * @param orderCnt 주문 카운트
     */
    private static void checkHours(List<OrderCntResponseDto> count,
                                   HashMap<Integer, Long> orderCnt) {
        for (int i = 0; i < 24; i++) {
            count.add(new OrderCntResponseDto(i, orderCnt.getOrDefault(i, 0L)));
        }
    }
}
