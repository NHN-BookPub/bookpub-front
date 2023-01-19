package com.bookpub.bookpubfront.coupontype.service.impl;

import com.bookpub.bookpubfront.coupontype.adaptor.CouponTypeAdaptor;
import com.bookpub.bookpubfront.coupontype.dto.response.GetCouponTypeResponseDto;
import com.bookpub.bookpubfront.coupontype.service.CouponTypeService;
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
public class CouponTypeServiceImpl implements CouponTypeService {
    private final CouponTypeAdaptor couponTypeAdaptor;

    @Override
    public List<GetCouponTypeResponseDto> getCouponTypes() {
        return couponTypeAdaptor.requestCouponTypes();
    }
}
