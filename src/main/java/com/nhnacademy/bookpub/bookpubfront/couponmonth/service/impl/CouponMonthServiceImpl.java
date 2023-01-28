package com.nhnacademy.bookpub.bookpubfront.couponmonth.service.impl;

import com.nhnacademy.bookpub.bookpubfront.couponmonth.adaptor.CouponMonthAdaptor;
import com.nhnacademy.bookpub.bookpubfront.couponmonth.dto.request.CreateCouponMonthRequestDto;
import com.nhnacademy.bookpub.bookpubfront.couponmonth.dto.request.ModifyCouponMonthRequestDto;
import com.nhnacademy.bookpub.bookpubfront.couponmonth.dto.response.GetCouponMonthResponseDto;
import com.nhnacademy.bookpub.bookpubfront.couponmonth.service.CouponMonthService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 이달의 쿠폰을 다루기 위한 서비스 구현체입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class CouponMonthServiceImpl implements CouponMonthService {
    private final CouponMonthAdaptor couponMonthAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetCouponMonthResponseDto> getCouponMonths() {
        return couponMonthAdaptor.requestCouponMonths();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCouponMonth(CreateCouponMonthRequestDto createRequestDto) {
        couponMonthAdaptor.requestAddCouponMonth(createRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyCouponMonth(ModifyCouponMonthRequestDto modifyRequestDto) {
        couponMonthAdaptor.requestModifyCouponMonth(modifyRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCouponMonth(Long monthNo) {
        couponMonthAdaptor.requestDeleteCouponMonth(monthNo);
    }
}
