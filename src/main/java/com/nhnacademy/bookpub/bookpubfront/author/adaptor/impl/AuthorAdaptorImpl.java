package com.nhnacademy.bookpub.bookpubfront.author.adaptor.impl;

import com.nhnacademy.bookpub.bookpubfront.author.adaptor.AuthorAdaptor;
import com.nhnacademy.bookpub.bookpubfront.author.dto.request.AddAuthorRequestDto;
import com.nhnacademy.bookpub.bookpubfront.author.dto.request.ModifyAuthorRequestDto;
import com.nhnacademy.bookpub.bookpubfront.author.dto.request.RestModifyAuthorRequestDto;
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
 * @author : 박경서, 정유진, 김서현
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class AuthorAdaptorImpl implements AuthorAdaptor {
    private final RestTemplate restTemplate;
    private static final String AUTHOR_URI = "/api/authors";
    private static final String AUTHOR_AUTH_URI = "/token/authors";

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetAuthorResponseDto> getAuthors(Pageable pageable) {
        String url = UriComponentsBuilder.fromHttpUrl(GateWayConfig.getGatewayUrl() + AUTHOR_AUTH_URI)
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAuthor(AddAuthorRequestDto request) {
        String url = GateWayConfig.getGatewayUrl() + AUTHOR_AUTH_URI;

        restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(request, Utils.makeHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyAuthor(ModifyAuthorRequestDto request) {
        String url = GateWayConfig.getGatewayUrl() + AUTHOR_AUTH_URI + "/" + request.getModifyAuthorNo();

        RestModifyAuthorRequestDto dto = new RestModifyAuthorRequestDto(
                request.getModifyAuthorName(), request.getModifyMainBook());

        restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(dto, Utils.makeHeader()),
                Void.class
        );
    }
}
