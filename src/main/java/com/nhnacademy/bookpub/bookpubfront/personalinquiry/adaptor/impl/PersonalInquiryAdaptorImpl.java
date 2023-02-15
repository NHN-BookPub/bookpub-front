package com.nhnacademy.bookpub.bookpubfront.personalinquiry.adaptor.impl;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import com.nhnacademy.bookpub.bookpubfront.personalinquiry.adaptor.PersonalInquiryAdaptor;
import com.nhnacademy.bookpub.bookpubfront.personalinquiry.dto.request.CreatePersonalInquiryRequestDto;
import com.nhnacademy.bookpub.bookpubfront.personalinquiry.dto.response.GetPersonalInquiryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.personalinquiry.dto.response.GetSimplePersonalInquiryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * shop 서버와 통신하기 위한 1대1문의 어댑터 구현체입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class PersonalInquiryAdaptorImpl implements PersonalInquiryAdaptor {
    private final MemberUtils memberUtils;
    private final RestTemplate restTemplate;
    private static final String TOKEN_PERSONAL_INQUIRY_API = "/token/personal-inquiries";

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetSimplePersonalInquiryResponseDto>
        requestMemberPersonalInquiries(Pageable pageable) {
        String url = UriComponentsBuilder
                .fromHttpUrl(GateWayConfig.getGatewayUrl() + TOKEN_PERSONAL_INQUIRY_API
                        + "/members/" + memberUtils.getMemberNo())
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        ResponseEntity<PageResponse<GetSimplePersonalInquiryResponseDto>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        new HttpEntity<>(Utils.makeHeader()),
                        new ParameterizedTypeReference<>() {
                        }
                );

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetSimplePersonalInquiryResponseDto>
        requestPersonalInquiries(Pageable pageable) {
        String url = UriComponentsBuilder
                .fromHttpUrl(GateWayConfig.getGatewayUrl() + TOKEN_PERSONAL_INQUIRY_API)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        ResponseEntity<PageResponse<GetSimplePersonalInquiryResponseDto>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        new HttpEntity<>(Utils.makeHeader()),
                        new ParameterizedTypeReference<>() {
                        }
                );

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetPersonalInquiryResponseDto requestPersonalInquiry(Long inquiryNo) {
        String url = GateWayConfig.getGatewayUrl() + TOKEN_PERSONAL_INQUIRY_API + "/"
                + inquiryNo + "/members/" + memberUtils.getMemberNo();

        ResponseEntity<GetPersonalInquiryResponseDto> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        new HttpEntity<>(Utils.makeHeader()),
                        new ParameterizedTypeReference<>() {
                        }
                );

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestCreatePersonalInquiry(CreatePersonalInquiryRequestDto createDto) {
        String url = GateWayConfig.getGatewayUrl() + TOKEN_PERSONAL_INQUIRY_API
                + "/members/" + memberUtils.getMemberNo();

        restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(createDto, Utils.makeHeader()),
                new ParameterizedTypeReference<>() {
                }
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestDeletePersonalInquiry(Long inquiryNo) {
        String url = GateWayConfig.getGatewayUrl() + TOKEN_PERSONAL_INQUIRY_API + "/" + inquiryNo
                + "/members/" + memberUtils.getMemberNo();

        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                new HttpEntity<>(Utils.makeHeader()),
                Void.class
        );
    }
}
