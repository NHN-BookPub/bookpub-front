package com.nhnacademy.bookpub.bookpubfront.purchase.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.purchase.adaptor.PurchaseAdaptor;
import com.nhnacademy.bookpub.bookpubfront.purchase.dto.request.CreatePurchaseRequestDto;
import com.nhnacademy.bookpub.bookpubfront.purchase.dto.response.GetPurchaseListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.response.GetAppliedMemberResponseDto;
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
 * 구매이력 아답터의 구현체입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class PurchaseAdaptorImpl implements PurchaseAdaptor {
    private final RestTemplate restTemplate;
    private static final String AUTH_PURCHASE_URL = "/token/purchases";

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetPurchaseListResponseDto> getPurchases(Pageable pageable) {
        String url =
                UriComponentsBuilder.fromHttpUrl(GateWayConfig.getGatewayUrl() + AUTH_PURCHASE_URL)
                        .queryParam("page", pageable.getPageNumber())
                        .queryParam("size", pageable.getPageSize())
                        .encode()
                        .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<PageResponse<GetPurchaseListResponseDto>>() {
                }
        ).getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetAppliedMemberResponseDto> createPurchase(CreatePurchaseRequestDto request) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + AUTH_PURCHASE_URL + "/absorption")
                .encode()
                .toUriString();

        HttpEntity<CreatePurchaseRequestDto> entity =
                new HttpEntity<>(request, makeHeader());

        return restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        entity,
                        new ParameterizedTypeReference<List<GetAppliedMemberResponseDto>>() {
                        })
                .getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetPurchaseListResponseDto> getPurchasesByProductNo(Long productNo, Pageable pageable) {
        String url = UriComponentsBuilder.fromHttpUrl(
                GateWayConfig.getGatewayUrl() + AUTH_PURCHASE_URL + "/" + productNo)
                .encode()
                .toUriString();

        ResponseEntity<PageResponse<GetPurchaseListResponseDto>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        new HttpEntity<>(makeHeader()),
                        new ParameterizedTypeReference<>() {});

        return response.getBody();
    }

}
