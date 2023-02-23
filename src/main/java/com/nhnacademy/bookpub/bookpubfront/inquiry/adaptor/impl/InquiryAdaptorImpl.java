package com.nhnacademy.bookpub.bookpubfront.inquiry.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;
import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.inquiry.adaptor.InquiryAdaptor;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.request.RestCreateInquiryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquiryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryMemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryProductResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * shop 서버와 통신하기 위한 상품문의 어댑터 구현체입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class InquiryAdaptorImpl implements InquiryAdaptor {
    private final RestTemplate restTemplate;

    private static final String INQUIRY_URL = "/api/inquiries";
    private static final String INQUIRY_AUTH_URL = "/token/inquiries";


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean requestVerifyPurchaseProduct(Long memberNo, Long productNo) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + INQUIRY_AUTH_URL + "/members/"
                                + memberNo + "/verify")
                .queryParam("productNo", productNo)
                .encode()
                .toUriString();

        ResponseEntity<Boolean> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                Boolean.class
        );

        return Boolean.TRUE.equals(response.getBody());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean requestVerifyCanView(Long inquiryNo, Long memberNo) {
        String url = GateWayConfig.getGatewayUrl() + INQUIRY_AUTH_URL + "/"
                + inquiryNo + "/members/" + memberNo;

        ResponseEntity<GetInquiryResponseDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<>() {
                }
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestSubmitInquiry(Long memberNo, RestCreateInquiryRequestDto request) {
        String url = GateWayConfig.getGatewayUrl() + INQUIRY_AUTH_URL + "/members/" + memberNo;

        restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(request, makeHeader()),
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String requestSaveInquiryImage(Long memberNo, MultipartFile image) {
        String url = GateWayConfig.getGatewayUrl() + INQUIRY_AUTH_URL + "/members/"
                + memberNo + "/image";

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", image.getResource());

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(body, makeHeader(MediaType.MULTIPART_FORM_DATA)),
                String.class
        );

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestCancelInquiryAnswer(Long inquiryNo) {
        String url = GateWayConfig.getGatewayUrl() + INQUIRY_AUTH_URL + "/" + inquiryNo + "/answer";

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
    public void requestCancelInquiry(Long inquiryNo, Long memberNo) {
        String url = GateWayConfig.getGatewayUrl() + INQUIRY_AUTH_URL + "/"
                + inquiryNo + "/members/" + memberNo;

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
    public void requestInquiryComplete(Long inquiryNo) {
        String url = GateWayConfig.getGatewayUrl() + INQUIRY_AUTH_URL + "/"
                + inquiryNo + "/complete";

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
    public PageResponse<GetInquirySummaryProductResponseDto> requestProductInquiryList(
            Pageable pageable, Long productNo) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + INQUIRY_URL + "/products/" + productNo)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        ResponseEntity<PageResponse<GetInquirySummaryProductResponseDto>> response =
                restTemplate.exchange(
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
    public PageResponse<GetInquirySummaryMemberResponseDto> requestInquiryMemberList(
            Pageable pageable, Long memberNo) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + INQUIRY_AUTH_URL + "/members/" + memberNo)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        ResponseEntity<PageResponse<GetInquirySummaryMemberResponseDto>> response =
                restTemplate.exchange(
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
    public PageResponse<GetInquirySummaryResponseDto> requestErrorInquiryList(Pageable pageable) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + INQUIRY_AUTH_URL + "/error")
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        ResponseEntity<PageResponse<GetInquirySummaryResponseDto>> response =
                restTemplate.exchange(
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
    public PageResponse<GetInquirySummaryResponseDto> requestInquiryList(
            Pageable pageable, String searchKeyFir, String searchValueFir,
            String searchKeySec, String searchValueSec) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + INQUIRY_AUTH_URL)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("searchKeyFir", searchKeyFir)
                .queryParam("searchValueFir", searchValueFir)
                .queryParam("searchKeySec", searchKeySec)
                .queryParam("searchValueSec", searchValueSec)
                .build().toUriString();

        ResponseEntity<PageResponse<GetInquirySummaryResponseDto>> response =
                restTemplate.exchange(
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
    public GetInquiryResponseDto requestPrivateInquiry(Long inquiryNo, Long memberNo) {


        String url = GateWayConfig.getGatewayUrl() + INQUIRY_AUTH_URL + "/"
                + inquiryNo + "/members/" + memberNo;

        ResponseEntity<GetInquiryResponseDto> response = restTemplate.exchange(
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
    public GetInquiryResponseDto requestPublicInquiry(Long inquiryNo) {
        String url = GateWayConfig.getGatewayUrl() + INQUIRY_URL + "/" + inquiryNo;

        ResponseEntity<GetInquiryResponseDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }
}
