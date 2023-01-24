package com.bookpub.bookpubfront.coupon.adaptor;

import com.bookpub.bookpubfront.coupon.dto.request.CreateCouponRequestDto;
import com.bookpub.bookpubfront.coupon.dto.response.GetCouponResponseDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 * api를 이용해 back 서버(shop)와 데이터를 주고받기 위해 만든 어댑터 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/

public interface CouponAdaptor {
    /**
     * 쿠폰을 등록하기 위한 메서드입니다.
     *
     * @param createRequestDto 등록할 쿠폰 정보
     */
    void requestAddCoupon(CreateCouponRequestDto createRequestDto);

    /**
     * 쿠폰 페이지를 조회하기 위한 메소드입니다.
     *
     * @param pageable  페이지 정보
     * @param searchKey 검색 조건
     * @param search    검색어
     * @return 쿠폰 Dto 정보를 담은 페이지
     */
    PageResponse<GetCouponResponseDto> requestCoupons(Pageable pageable, String searchKey, String search);
}
