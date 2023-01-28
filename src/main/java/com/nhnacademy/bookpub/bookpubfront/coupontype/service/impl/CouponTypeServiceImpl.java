package com.nhnacademy.bookpub.bookpubfront.coupontype.service.impl;

import com.nhnacademy.bookpub.bookpubfront.coupontype.adaptor.CouponTypeAdaptor;
import com.nhnacademy.bookpub.bookpubfront.coupontype.dto.response.GetCouponTypeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.coupontype.service.CouponTypeService;
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
public class CouponTypeServiceImpl implements CouponTypeService {
    private final CouponTypeAdaptor couponTypeAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetCouponTypeResponseDto> getCouponTypes() {
        return couponTypeAdaptor.requestCouponTypes();
    }
}
