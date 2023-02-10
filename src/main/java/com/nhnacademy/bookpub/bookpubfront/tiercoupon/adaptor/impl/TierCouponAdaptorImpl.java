package com.nhnacademy.bookpub.bookpubfront.tiercoupon.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.tiercoupon.adaptor.TierCouponAdaptor;
import com.nhnacademy.bookpub.bookpubfront.tiercoupon.dto.request.CreateTierCouponRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tiercoupon.dto.response.GetTierCouponResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * TierCoupon 이 api 서버와 연동하기 위한 구현체.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class TierCouponAdaptorImpl implements TierCouponAdaptor {

    private final RestTemplate restTemplate;

    private static final String TIER_COUPON_URL = "/api/tier-coupons";
    private static final String TIER_COUPON_AUTH_URL = "/token/tier-coupons";


    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetTierCouponResponseDto> requestTierCoupons(Pageable pageable) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + TIER_COUPON_AUTH_URL)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode().toUriString();

        ResponseEntity<PageResponse<GetTierCouponResponseDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestAddTierCoupon(CreateTierCouponRequestDto createRequestDto) {
        String url = GateWayConfig.getGatewayUrl() + TIER_COUPON_AUTH_URL;

        restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(createRequestDto, makeHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestDeleteTierCoupon(Integer tierNo, Long templateNo) {
        String url = GateWayConfig.getGatewayUrl() + TIER_COUPON_AUTH_URL + "?&tierNo=" + tierNo
                + "&templateNo=" + templateNo;

        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                new HttpEntity<>(makeHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Long> requestTierCouponsByTierNo(Integer tierNo) {
        String url = GateWayConfig.getGatewayUrl() + TIER_COUPON_URL + "/" + tierNo;

        ResponseEntity<List<Long>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }
}
