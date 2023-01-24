package com.bookpub.bookpubfront.coupon.service;

import com.bookpub.bookpubfront.coupon.dto.request.CreateCouponRequestDto;
import com.bookpub.bookpubfront.coupon.dto.response.GetCouponResponseDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 * 쿠폰을 다루기 위한 서비스 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface CouponService {
    /**
     * 쿠폰을 생성하기 위한 메서드입니다.
     *
     * @param createRequestDto 쿠폰 생성에 필요한 정보를 담은 Dto
     */
    void createCoupon(CreateCouponRequestDto createRequestDto);

    /**
     * 쿠폰 페이지를 조회하기 위한 메서드입니다.
     *
     * @param pageable  페이지 정보
     * @param searchKey 검색 조건
     * @param search    검색어
     * @return 쿠폰을 조회할 수 있는 Dto를 담은 페이지
     */
    PageResponse<GetCouponResponseDto> getCoupons(Pageable pageable, String searchKey, String search);
}
