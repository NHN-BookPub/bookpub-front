package com.nhnacademy.bookpub.bookpubfront.sales.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig.getGatewayUrl;
import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;
import com.nhnacademy.bookpub.bookpubfront.sales.adaptor.SalesAdaptor;
import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.OrderCntResponseDto;
import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.SaleProductCntDto;
import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.TotalSaleDto;
import com.nhnacademy.bookpub.bookpubfront.sales.dto.response.TotalSaleYearDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 매출관련 실 구현체 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class SalesAdaptorImpl implements SalesAdaptor {
    private final RestTemplate restTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TotalSaleDto> getSalesRequest(LocalDateTime start,
                                              LocalDateTime end) {
        String url = UriComponentsBuilder.fromHttpUrl(getGatewayUrl() + "/token/sales")
                .queryParam("start", start)
                .queryParam("end", end)
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<List<TotalSaleDto>>() {
                }
        ).getBody();
    }

    @Override
    public List<OrderCntResponseDto> orderCntRequest() {
        String url = UriComponentsBuilder.fromHttpUrl(getGatewayUrl() + "/token/order-count")
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<List<OrderCntResponseDto>>() {
                }
        ).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TotalSaleYearDto> getSalesYearRequest(LocalDateTime start,
                                                      LocalDateTime end) {
        String url = UriComponentsBuilder.fromHttpUrl(getGatewayUrl() + "/token/sales-month")
                .queryParam("start", start)
                .queryParam("end", end)
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<List<TotalSaleYearDto>>() {
                }
        ).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SaleProductCntDto> requestSaleProductRankCount(
            LocalDateTime start, LocalDateTime end) {
        String url = UriComponentsBuilder.fromHttpUrl(getGatewayUrl() + "/token/sale-product-rank")
                .queryParam("start", start)
                .queryParam("end", end)
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<List<SaleProductCntDto>>() {
                }
        ).getBody();
    }
}
