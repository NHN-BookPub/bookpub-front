package com.bookpub.bookpubfront.couponstatecode.service.impl;

import com.bookpub.bookpubfront.couponstatecode.adaptor.CouponStateCodeAdaptor;
import com.bookpub.bookpubfront.couponstatecode.dto.response.GetCouponStateCodeResponseDto;
import com.bookpub.bookpubfront.couponstatecode.service.CouponStateCodeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class CouponStateCodeServiceImpl implements CouponStateCodeService {

    private final CouponStateCodeAdaptor couponStateCodeAdaptor;

    @Override
    public List<GetCouponStateCodeResponseDto> getCouponStateCodes() {
        return couponStateCodeAdaptor.requestCouponStateCodes();
    }
}
