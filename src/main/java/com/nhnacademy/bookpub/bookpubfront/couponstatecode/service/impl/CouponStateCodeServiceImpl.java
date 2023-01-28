package com.nhnacademy.bookpub.bookpubfront.couponstatecode.service.impl;

import com.nhnacademy.bookpub.bookpubfront.couponstatecode.adaptor.CouponStateCodeAdaptor;
import com.nhnacademy.bookpub.bookpubfront.couponstatecode.dto.response.GetCouponStateCodeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.couponstatecode.service.CouponStateCodeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 쿠폰템플릿을 다루기 위한 서비스 구현체입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class CouponStateCodeServiceImpl implements CouponStateCodeService {

    private final CouponStateCodeAdaptor couponStateCodeAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetCouponStateCodeResponseDto> getCouponStateCodes() {
        return couponStateCodeAdaptor.requestCouponStateCodes();
    }
}
