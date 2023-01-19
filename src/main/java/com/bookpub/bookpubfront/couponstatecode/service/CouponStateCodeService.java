package com.bookpub.bookpubfront.couponstatecode.service;

import com.bookpub.bookpubfront.couponstatecode.dto.response.GetCouponStateCodeResponseDto;
import java.util.List;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface CouponStateCodeService {
    List<GetCouponStateCodeResponseDto> getCouponStateCodes();
}
