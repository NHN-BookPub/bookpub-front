package com.bookpub.bookpubfront.coupontemplate.adaptor.impl;

import com.bookpub.bookpubfront.config.GateWayConfig;
import com.bookpub.bookpubfront.coupontemplate.adaptor.CouponTemplateAdaptor;
import com.bookpub.bookpubfront.coupontemplate.dto.request.CreateCouponTemplateRequestDto;
import com.bookpub.bookpubfront.coupontemplate.dto.request.ModifyCouponTemplateRequestDto;
import com.bookpub.bookpubfront.coupontemplate.dto.response.GetCouponTemplateResponseDto;
import com.bookpub.bookpubfront.coupontemplate.dto.response.GetDetailCouponTemplateResponseDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class CouponTemplateAdaptorImpl implements CouponTemplateAdaptor {
    private final GateWayConfig gateWayConfig;
    private final RestTemplate restTemplate;
    private static final String COUPON_TEMPLATE_URL = "/api/coupon-templates";


    public PageResponse<GetCouponTemplateResponseDto> requestCouponTemplates(Pageable pageable) {
        String url = UriComponentsBuilder.fromHttpUrl(gateWayConfig.getGatewayUrl() + COUPON_TEMPLATE_URL)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .encode()
                .toUriString();

        ResponseEntity<PageResponse<GetCouponTemplateResponseDto>> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeaders()),
                new ParameterizedTypeReference<>() {
                });

        checkError(response);

        return response.getBody();
    }

    @Override
    public GetDetailCouponTemplateResponseDto requestDetailCouponTemplate(Long templateNo) {
        String url = gateWayConfig.getGatewayUrl() + COUPON_TEMPLATE_URL + "/details/" + templateNo;

        ResponseEntity<GetDetailCouponTemplateResponseDto> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(makeHeaders()),
                new ParameterizedTypeReference<>() {
                });

        checkError(response);

        return response.getBody();
    }

    public void requestAddCouponTemplate(CreateCouponTemplateRequestDto createRequestDto) {

        String url = gateWayConfig.getGatewayUrl() + COUPON_TEMPLATE_URL;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("createRequestDto", createRequestDto.transform());
        body.add("image", createRequestDto.getTemplateImage().getResource());

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Void.class
        );

        checkError(response);
    }

    @Override
    public void requestModifyCouponTemplate(Long templateNo, ModifyCouponTemplateRequestDto modifyRequestDto) {
        String url = gateWayConfig.getGatewayUrl() + COUPON_TEMPLATE_URL + "/" + templateNo;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("modifyRequestDto", modifyRequestDto.transform());
        body.add("image", modifyRequestDto.getTemplateImage().getResource());

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                Void.class
        );

        checkError(response);
    }

    private static HttpHeaders makeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }

    private static <T> void checkError(ResponseEntity<T> response) {
        HttpStatus statusCode = response.getStatusCode();

        if (statusCode.is4xxClientError() || statusCode.is5xxServerError()) {
            throw new RuntimeException();
        }
    }


}
