package com.nhnacademy.bookpub.bookpubfront.coupontemplate.adaptor.impl;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.adaptor.CouponTemplateAdaptor;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.request.CreateCouponTemplateRequestDto;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.request.ModifyCouponTemplateRequestDto;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.response.GetCouponTemplateResponseDto;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.response.GetDetailCouponTemplateResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 관리자 페이지의 쿠폰 템플릿을 관리하기 위한 어댑터입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class CouponTemplateAdaptorImpl implements CouponTemplateAdaptor {
    private final RestTemplate restTemplate;
    private static final String COUPON_TEMPLATE_URL = "/api/coupon-templates";
    private static final String COUPON_TEMPLATE_AUTH_URL = "/token/coupon-templates";


    /**
     * {@inheritDoc}
     */
    public PageResponse<GetCouponTemplateResponseDto> requestCouponTemplates(Pageable pageable) {
        String url = UriComponentsBuilder.fromHttpUrl(GateWayConfig.getGatewayUrl() + COUPON_TEMPLATE_AUTH_URL)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        ResponseEntity<PageResponse<GetCouponTemplateResponseDto>> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetDetailCouponTemplateResponseDto requestDetailCouponTemplate(Long templateNo) {
        String url = GateWayConfig.getGatewayUrl() + COUPON_TEMPLATE_AUTH_URL + "/" + templateNo;

        ResponseEntity<GetDetailCouponTemplateResponseDto> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    public void requestAddCouponTemplate(CreateCouponTemplateRequestDto createRequestDto) {

        String url = GateWayConfig.getGatewayUrl() + COUPON_TEMPLATE_AUTH_URL;

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("createRequestDto", createRequestDto.transform());
        body.add("image", createRequestDto.getTemplateImage().getResource());

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, Utils.makeHeader(MediaType.MULTIPART_FORM_DATA));

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
    public void requestModifyCouponTemplate(Long templateNo, ModifyCouponTemplateRequestDto modifyRequestDto) {
        String url = GateWayConfig.getGatewayUrl() + COUPON_TEMPLATE_AUTH_URL + "/" + templateNo;

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("modifyRequestDto", modifyRequestDto.transform());
        body.add("image", modifyRequestDto.getTemplateImage().getResource());

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, Utils.makeHeader(MediaType.MULTIPART_FORM_DATA));

        restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                Void.class
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existTemplateCheck(Long templateNo) {
        String url = GateWayConfig.getGatewayUrl() + COUPON_TEMPLATE_AUTH_URL + "/" + templateNo;

        HttpStatus response;
        try {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(Utils.makeHeader()),
                    Object.class
            ).getStatusCode();
        } catch (HttpStatusCodeException e) {
            response = HttpStatus.valueOf(e.getRawStatusCode());
        }

        return response.is2xxSuccessful();
    }
}
