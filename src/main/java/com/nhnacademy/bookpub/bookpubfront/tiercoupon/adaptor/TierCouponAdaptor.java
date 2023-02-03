package com.nhnacademy.bookpub.bookpubfront.tiercoupon.adaptor;

import com.nhnacademy.bookpub.bookpubfront.tiercoupon.dto.request.CreateTierCouponRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tiercoupon.dto.response.GetTierCouponResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 * TierCoupon 이 api 서버와 연동하기 위한 인터페이스.
 *
 * @author : 김서현
 * @since : 1.0
 **/
public interface TierCouponAdaptor {

    /**
     * 등급 쿠폰 페이지를 조회하기 위한 메서드입니다.
     *
     * @param pageable 페이지.
     * @return 등급 쿠폰 DTO 를 담은 페이지.
     */
    PageResponse<GetTierCouponResponseDto> requestTierCoupons(Pageable pageable);

    /**
     * 등급 쿠폰을 등록하기 위한 메서드입니다.
     *
     * @param createRequestDto 등록할 등급 쿠폰 정보를 담은 DTO.
     */
    void requestAddTierCoupon(CreateTierCouponRequestDto createRequestDto);

    /**
     * 등급 쿠폰을 삭제하기 위한 메서드입니다.
     *
     * @param tierNo     등급 번호
     * @param templateNo 쿠폰 템플릿 번호
     */
    void requestDeleteTierCoupon(Integer tierNo, Long templateNo);
}
