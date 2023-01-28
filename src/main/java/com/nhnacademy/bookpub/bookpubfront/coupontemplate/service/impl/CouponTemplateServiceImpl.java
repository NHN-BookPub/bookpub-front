package com.nhnacademy.bookpub.bookpubfront.coupontemplate.service.impl;

import com.nhnacademy.bookpub.bookpubfront.coupontemplate.adaptor.CouponTemplateAdaptor;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.request.CreateCouponTemplateRequestDto;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.request.ModifyCouponTemplateRequestDto;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.response.GetCouponTemplateResponseDto;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.response.GetDetailCouponTemplateResponseDto;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.response.GetDownloadInfo;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.service.CouponTemplateService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 쿠폰템플릿을 다루기 위한 서비스 구현체입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class CouponTemplateServiceImpl implements CouponTemplateService {
    private final RestTemplate restTemplate;
    private final CouponTemplateAdaptor couponTemplateAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetCouponTemplateResponseDto> getCouponTemplates(Pageable pageable) {
        return couponTemplateAdaptor.requestCouponTemplates(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetDetailCouponTemplateResponseDto getDetailCouponTemplate(Long templateNo) {
        return couponTemplateAdaptor.requestDetailCouponTemplate(templateNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCouponTemplate(CreateCouponTemplateRequestDto createRequestDto) {

        couponTemplateAdaptor.requestAddCouponTemplate(createRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyCouponTemplate(Long templateNo, ModifyCouponTemplateRequestDto modifyRequestDto) {
        couponTemplateAdaptor.requestModifyCouponTemplate(templateNo, modifyRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existTemplateCheck(Long templateNo) {
        return couponTemplateAdaptor.existTemplateCheck(templateNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetDownloadInfo downloadInfo(Long templateNo) {
        return couponTemplateAdaptor.requestDownloadFile(templateNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] downloadFile(String path, String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-Token", token);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

        ResponseEntity<byte[]> response = restTemplate.exchange(
                path,
                HttpMethod.GET,
                new HttpEntity<String>(null, headers),
                byte[].class
        );

        return response.getBody();
    }
}
