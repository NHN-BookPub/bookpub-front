package com.nhnacademy.bookpub.bookpubfront.author.adaptor.impl;

import com.nhnacademy.bookpub.bookpubfront.author.adaptor.AuthorAdaptor;
import com.nhnacademy.bookpub.bookpubfront.author.dto.response.GetAuthorResponseDto;
import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * AuthorAdaptor 구현체.
 *
 * @author : 박경서, 정유진
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class AuthorAdaptorImpl implements AuthorAdaptor {
    private final RestTemplate restTemplate;
    private final GateWayConfig gateWayConfig;
    private static final String AUTHOR_URI = "/api/authors";

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetAuthorResponseDto> getAuthors(Pageable pageable) {
        String url = UriComponentsBuilder.fromHttpUrl(gateWayConfig.getGatewayUrl() + AUTHOR_URI)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<PageResponse<GetAuthorResponseDto>>() {
                }
        ).getBody();
    }

}
