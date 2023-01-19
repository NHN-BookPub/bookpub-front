package com.bookpub.bookpubfront.coupontype.service;

import com.bookpub.bookpubfront.coupontype.dto.response.GetCouponTypeResponseDto;
import java.util.List;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface CouponTypeService {
    List<GetCouponTypeResponseDto> getCouponTypes();
}
