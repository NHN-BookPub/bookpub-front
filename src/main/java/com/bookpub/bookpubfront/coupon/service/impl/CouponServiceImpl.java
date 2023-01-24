package com.bookpub.bookpubfront.coupon.service.impl;

import com.bookpub.bookpubfront.coupon.adaptor.CouponAdaptor;
import com.bookpub.bookpubfront.coupon.dto.request.CreateCouponRequestDto;
import com.bookpub.bookpubfront.coupon.dto.response.GetCouponResponseDto;
import com.bookpub.bookpubfront.coupon.service.CouponService;
import com.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 쿠폰을 다루기 위한 서비스 구현체입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponAdaptor couponAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCoupon(CreateCouponRequestDto createRequestDto) {
        couponAdaptor.requestAddCoupon(createRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetCouponResponseDto> getCoupons(Pageable pageable, String searchKey, String search) {
        return couponAdaptor.requestCoupons(pageable, searchKey, search);
    }
}
