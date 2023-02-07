package com.nhnacademy.bookpub.bookpubfront.review.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductSimpleResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.adaptor.ReviewAdaptor;
import com.nhnacademy.bookpub.bookpubfront.review.dto.request.CreateReviewRequestDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.request.ModifyReviewRequestDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.response.GetMemberReviewResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.response.GetProductReviewInfoResponseDto;
import com.nhnacademy.bookpub.bookpubfront.review.dto.response.GetProductReviewResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 상품평 어댑터 구현체입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class ReviewAdaptorImpl implements ReviewAdaptor {
    private final RestTemplate restTemplate;
    private static final String REVIEW_URL = "/api/reviews";
    private static final String REVIEW_AUTH_URL = "/token/reviews";

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetMemberReviewResponseDto> requestMemberReviews(
            Long memberNo, Pageable pageable) {
        String url = UriComponentsBuilder
                .fromHttpUrl(GateWayConfig.getGatewayUrl()
                        + REVIEW_AUTH_URL + "/member/" + memberNo)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        ResponseEntity<PageResponse<GetMemberReviewResponseDto>> response = restTemplate.exchange(
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
    public PageResponse<GetProductReviewResponseDto> requestProductReviews(
            Long productNo, Pageable pageable) {
        String url = UriComponentsBuilder
                .fromHttpUrl(GateWayConfig.getGatewayUrl() + REVIEW_URL + "/product/" + productNo)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        ResponseEntity<PageResponse<GetProductReviewResponseDto>> response = restTemplate.exchange(
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
    public PageResponse<GetProductSimpleResponseDto> requestMemberWritableReviews(
            Long memberNo, Pageable pageable) {
        String url = UriComponentsBuilder
                .fromHttpUrl(GateWayConfig.getGatewayUrl() + REVIEW_AUTH_URL
                        + "/member/" + memberNo + "/writable")
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();


        ResponseEntity<PageResponse<GetProductSimpleResponseDto>> response = restTemplate.exchange(
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
    public GetMemberReviewResponseDto requestReview(Long reviewNo) {
        String url = GateWayConfig.getGatewayUrl() + REVIEW_URL + "/" + reviewNo;

        ResponseEntity<GetMemberReviewResponseDto> response = restTemplate.exchange(
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
    public GetProductReviewInfoResponseDto requestProductReviewInfo(Long productNo) {
        String url = GateWayConfig.getGatewayUrl() + REVIEW_URL + "/info/product/" + productNo;

        ResponseEntity<GetProductReviewInfoResponseDto> response = restTemplate.exchange(
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
    public void requestCreateReview(CreateReviewRequestDto request, Long memberNo) {
        String url = GateWayConfig.getGatewayUrl() + REVIEW_AUTH_URL + "/members/" + memberNo;

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("createRequestDto", request.transform(memberNo));
        body.add("image", request.getReviewImage().getResource());

        HttpEntity<MultiValueMap<String, Object>> entity =
                new HttpEntity<>(body, makeHeader(MediaType.MULTIPART_FORM_DATA));

        restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestDeleteReview(Long reviewNo) {
        Long memberNo = Long.parseLong((String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());

        String url = GateWayConfig.getGatewayUrl() + REVIEW_AUTH_URL + "/" + reviewNo
                + "/members/" + memberNo;

        restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(makeHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestDeleteReviewImage(Long reviewNo) {
        Long memberNo = Long.parseLong((String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());

        String url = GateWayConfig.getGatewayUrl()
                + REVIEW_AUTH_URL + "/" + reviewNo + "/file/members/" + memberNo;

        restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(makeHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestModifyReview(Long reviewNo, ModifyReviewRequestDto request) {
        Long memberNo = Long.parseLong((String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());

        String url = GateWayConfig.getGatewayUrl()
                + REVIEW_AUTH_URL + "/" + reviewNo + "/content/members/" + memberNo;

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("modifyRequestDto", request.transform());
        body.add("image", request.getReviewImage().getResource());

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(
                body, Utils.makeHeader(MediaType.MULTIPART_FORM_DATA));

        restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                Void.class
        );
    }
}
