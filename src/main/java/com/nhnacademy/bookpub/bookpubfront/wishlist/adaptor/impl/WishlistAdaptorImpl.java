package com.nhnacademy.bookpub.bookpubfront.wishlist.adaptor.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.exception.ServerErrorException;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import com.nhnacademy.bookpub.bookpubfront.wishlist.adaptor.WishlistAdaptor;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.request.CreateWishlistRequestDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.request.DeleteWishlistRequestDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.request.ModifyWishlistAlarmRequestDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.response.GetWishlistCountResponseDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.response.GetWishlistResponseDto;
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
 * wishlistAdaptor 구현체.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class WishlistAdaptorImpl implements WishlistAdaptor {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private static final String WISHLIST_TOKEN_URL = "/token/members/";
    private static final String WISHLIST_URL = "/wishlist";

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestCreateWishlist(Long memberNo, CreateWishlistRequestDto request) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + WISHLIST_TOKEN_URL + memberNo
                                + WISHLIST_URL)
                .encode()
                .toUriString();

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
    public PageResponse<GetWishlistResponseDto> requestWishlistsByMember(Long memberNo,
            Pageable pageable) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + WISHLIST_TOKEN_URL + memberNo
                                + WISHLIST_URL)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<PageResponse<GetWishlistResponseDto>>() {
                }
        ).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestDeleteWishlist(Long memberNo, DeleteWishlistRequestDto request) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + WISHLIST_TOKEN_URL + memberNo
                                + WISHLIST_URL)
                .encode()
                .toUriString();

        try {
            restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    new HttpEntity<>(objectMapper.writeValueAsString(request), Utils.makeHeader()),
                    Void.class
            );
        } catch (JsonProcessingException e) {
            throw new ServerErrorException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestModifyWishlistAlarm(Long memberNo, ModifyWishlistAlarmRequestDto request) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + WISHLIST_TOKEN_URL + memberNo
                                + WISHLIST_URL)
                .encode()
                .toUriString();

        try {
            restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    new HttpEntity<>(objectMapper.writeValueAsString(request), Utils.makeHeader()),
                    Void.class
            );
        } catch (JsonProcessingException e) {
            throw new ServerErrorException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetWishlistCountResponseDto> requestWishListCount(Integer categoryNo,
            Pageable pageable) {

        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + "/token/wishlist")
                .queryParam("categoryNo", categoryNo)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<PageResponse<GetWishlistCountResponseDto>>() {
                }
        ).getBody();
    }
}
