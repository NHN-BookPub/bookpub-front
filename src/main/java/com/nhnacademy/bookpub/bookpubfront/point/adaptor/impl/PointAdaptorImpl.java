package com.nhnacademy.bookpub.bookpubfront.point.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig.getGatewayUrl;
import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.bookpub.bookpubfront.point.adaptor.PointAdaptor;
import com.nhnacademy.bookpub.bookpubfront.point.dto.request.PointGiftRequestDto;
import com.nhnacademy.bookpub.bookpubfront.point.dto.response.GetPointAdminResponseDto;
import com.nhnacademy.bookpub.bookpubfront.point.dto.response.GetPointResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.time.LocalDateTime;
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
    private final ObjectMapper objectMapper;
    private static final String TOKEN_URL = "/token/point";

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetPointResponseDto> getMemberPointHistory(
            Pageable pageable, Long memberNo, String type) {

        String url = UriComponentsBuilder.fromHttpUrl(getGatewayUrl() + TOKEN_URL)
                .path("/" + memberNo)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("type", type)
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
    public void giftPoint(Long memberNo, PointGiftRequestDto requestDto) {
        restTemplate.exchange(
                getGatewayUrl() + TOKEN_URL + "/" + memberNo,
                HttpMethod.POST,
                new HttpEntity<>(requestDto, makeHeader()),
                Void.class
        );
    }

    @Override
    public PageResponse<GetPointAdminResponseDto> getPoints(Pageable pageable, LocalDateTime start,
                                                            LocalDateTime end) {
        String url = UriComponentsBuilder.fromHttpUrl(getGatewayUrl() + "/token/points")
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("start", start)
                .queryParam("end", end)
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<PageResponse<GetPointAdminResponseDto>>() {
                }
        ).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetPointAdminResponseDto> getPointsBySearch(Pageable pageable,
                                                                    LocalDateTime start,
                                                                    LocalDateTime end,
                                                                    String key) {
        String url = UriComponentsBuilder.fromHttpUrl(getGatewayUrl() + "/token/points/" + key)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("start", start)
                .queryParam("end", end)
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<PageResponse<GetPointAdminResponseDto>>() {
                }
        ).getBody();
    }

}
