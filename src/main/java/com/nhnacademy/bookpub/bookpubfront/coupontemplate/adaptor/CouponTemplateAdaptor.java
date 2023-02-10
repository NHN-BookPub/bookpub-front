package com.nhnacademy.bookpub.bookpubfront.coupontemplate.adaptor;

import com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.request.CreateCouponTemplateRequestDto;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.request.ModifyCouponTemplateRequestDto;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.response.GetCouponTemplateResponseDto;
import com.nhnacademy.bookpub.bookpubfront.coupontemplate.dto.response.GetDetailCouponTemplateResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 * api를 이용해 back 서버(shop)와 데이터를 주고받기 위해 만든 어댑터 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface CouponTemplateAdaptor {
    /**
     * 쿠폰템플릿 페이지를 조회하기 위한 메소드입니다.
     *
     * @param pageable 페이지 정보
     * @return 쿠폰템플릿 리스트를 담은 페이지들
     */
    PageResponse<GetCouponTemplateResponseDto> requestCouponTemplates(Pageable pageable);

    /**
     * 쿠폰템플릿 상세 정보를 조회하기 위한 메소드입니다.
     *
     * @param templateNo 조회할 템플릿 번호
     * @return 쿠폰템플릿 상세정보 dto
     */
    GetDetailCouponTemplateResponseDto requestDetailCouponTemplate(Long templateNo);

    /**
     * 쿠폰템플릿 등록을 위한 메소드입니다.
     *
     * @param createRequestDto 등록할 쿠폰템플릿 정보를 담은 dto
     */
    void requestAddCouponTemplate(CreateCouponTemplateRequestDto createRequestDto);

    /**
     * 쿠폰템플릿 수정을 위한 메소드입니다.
     *
     * @param templateNo       쿠폰템플릿 번호
     * @param modifyRequestDto 수정할 쿠폰템플릿 정보를 담은 dto
     */
    void requestModifyCouponTemplate(Long templateNo, ModifyCouponTemplateRequestDto modifyRequestDto);

    /**
     * 쿠폰템플릿이 존재하는지 확인하기 위한 메소드입니다.
     *
     * @param templateNo 쿠폰템플릿 번호
     * @return the boolean
     */
    boolean existTemplateCheck(Long templateNo);
}
