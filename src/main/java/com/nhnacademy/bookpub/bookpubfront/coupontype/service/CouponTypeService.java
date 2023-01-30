package com.nhnacademy.bookpub.bookpubfront.coupontype.service;

import com.nhnacademy.bookpub.bookpubfront.coupontype.dto.response.GetCouponTypeResponseDto;
import java.util.List;

/**
 * 쿠폰유형을 다루기 위한 서비스 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface CouponTypeService {
    /**
     * 쿠폰유형 리스트를 조회하기 위한 메소드입니다.
     *
     * @return 쿠폰유형 리스트
     */
    List<GetCouponTypeResponseDto> getCouponTypes();
}
