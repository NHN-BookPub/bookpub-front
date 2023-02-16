package com.nhnacademy.bookpub.bookpubfront.file.adaptor.impl;

import com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig;
import com.nhnacademy.bookpub.bookpubfront.file.adaptor.FileAdaptor;
import com.nhnacademy.bookpub.bookpubfront.file.dto.response.GetDownloadInfo;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * FileAdaptor 구현체.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class FileAdaptorImpl implements FileAdaptor {
    private final RestTemplate restTemplate;
    private static final String COUPON_TEMPLATE_AUTH_URL = "/token/coupon-templates";
    private static final String EBOOK_AUTH_URL = "/token/downloads/e-book";

    /**
     * {@inheritDoc}
     */
    @Override
    public GetDownloadInfo requestCouponTemplateInfo(Long templateNo) {
        String url = GateWayConfig.getGatewayUrl() + COUPON_TEMPLATE_AUTH_URL + "/" + templateNo + "/download";

        ResponseEntity<GetDownloadInfo> response = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                GetDownloadInfo.class
        );

        return response.getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetDownloadInfo requestEBookInfo(Long productNo, Long memberNo) {
        String url = UriComponentsBuilder.fromHttpUrl(
                        GateWayConfig.getGatewayUrl() + EBOOK_AUTH_URL + "/" + productNo + "/" + memberNo)
                .encode()
                .toUriString();
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                GetDownloadInfo.class
        ).getBody();
    }
}
