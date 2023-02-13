package com.nhnacademy.bookpub.bookpubfront.point.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig.getGatewayUrl;
import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;

import com.nhnacademy.bookpub.bookpubfront.point.adaptor.PointAdaptor;
import com.nhnacademy.bookpub.bookpubfront.point.dto.request.PointGiftRequestDto;
import com.nhnacademy.bookpub.bookpubfront.point.dto.response.GetPointResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 포인트 어댑터 구현체.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class PointAdaptorImpl implements PointAdaptor {
    private final RestTemplate restTemplate;
    private static final String TOKEN_URL = "/token/point";

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetPointResponseDto> getMemberPointHistory(
            Pageable pageable, Long memberNo, String type) {

        String url = UriComponentsBuilder.fromHttpUrl(getGatewayUrl() + TOKEN_URL)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("type", type)
                .queryParam("memberNo", memberNo)
                .encode().toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<PageResponse<GetPointResponseDto>>() {
                }
        ).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void giftPoint(PointGiftRequestDto requestDto) {
        restTemplate.exchange(
                getGatewayUrl() + TOKEN_URL,
                HttpMethod.POST,
                new HttpEntity<>(requestDto, makeHeader()),
                Void.class
        );
    }
}
