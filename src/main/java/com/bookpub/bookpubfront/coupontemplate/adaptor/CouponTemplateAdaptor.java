package com.bookpub.bookpubfront.coupontemplate.adaptor;

import com.bookpub.bookpubfront.coupontemplate.dto.request.CreateCouponTemplateRequestDto;
import com.bookpub.bookpubfront.coupontemplate.dto.request.ModifyCouponTemplateRequestDto;
import com.bookpub.bookpubfront.coupontemplate.dto.response.GetCouponTemplateResponseDto;
import com.bookpub.bookpubfront.coupontemplate.dto.response.GetDetailCouponTemplateResponseDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface CouponTemplateAdaptor {
    PageResponse<GetCouponTemplateResponseDto> requestCouponTemplates(Pageable pageable);

    GetDetailCouponTemplateResponseDto requestDetailCouponTemplate(Long templateNo);

    void requestAddCouponTemplate(CreateCouponTemplateRequestDto createRequestDto);

    void requestModifyCouponTemplate(Long templateNo, ModifyCouponTemplateRequestDto modifyRequestDto);
}
