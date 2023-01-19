package com.bookpub.bookpubfront.coupontemplate.service;

import com.bookpub.bookpubfront.coupontemplate.dto.request.CreateCouponTemplateRequestDto;
import com.bookpub.bookpubfront.coupontemplate.dto.request.ModifyCouponTemplateRequestDto;
import com.bookpub.bookpubfront.coupontemplate.dto.response.GetCouponTemplateResponseDto;
import com.bookpub.bookpubfront.coupontemplate.dto.response.GetDetailCouponTemplateResponseDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import java.io.IOException;
import org.springframework.data.domain.Pageable;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface CouponTemplateService {
    PageResponse<GetCouponTemplateResponseDto> getCouponTemplates(Pageable pageable);

    GetDetailCouponTemplateResponseDto getDetailCouponTemplate(Long templateNo);

    void createCouponTemplate(CreateCouponTemplateRequestDto createRequestDto) throws IOException;

    void modifyCouponTemplate(Long templateNo, ModifyCouponTemplateRequestDto modifyRequestDto);


}
