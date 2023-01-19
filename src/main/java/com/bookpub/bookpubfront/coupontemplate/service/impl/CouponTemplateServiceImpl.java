package com.bookpub.bookpubfront.coupontemplate.service.impl;

import com.bookpub.bookpubfront.coupontemplate.adaptor.CouponTemplateAdaptor;
import com.bookpub.bookpubfront.coupontemplate.dto.request.CreateCouponTemplateRequestDto;
import com.bookpub.bookpubfront.coupontemplate.dto.request.ModifyCouponTemplateRequestDto;
import com.bookpub.bookpubfront.coupontemplate.dto.response.GetCouponTemplateResponseDto;
import com.bookpub.bookpubfront.coupontemplate.dto.response.GetDetailCouponTemplateResponseDto;
import com.bookpub.bookpubfront.coupontemplate.service.CouponTemplateService;
import com.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class CouponTemplateServiceImpl implements CouponTemplateService {
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
}
