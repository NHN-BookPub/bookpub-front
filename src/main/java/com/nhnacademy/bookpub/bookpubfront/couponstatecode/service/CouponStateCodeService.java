package com.nhnacademy.bookpub.bookpubfront.couponstatecode.service;

import com.nhnacademy.bookpub.bookpubfront.couponstatecode.dto.response.GetCouponStateCodeResponseDto;
import java.util.List;

/**
 * 쿠폰상태코드를 다루기 위한 서비스 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface CouponStateCodeService {
    /**
     * 쿠폰상태코드를 조회하기 위한 메소드입니다.
     *
     * @return 쿠폰상태코드 리스트
     */
    List<GetCouponStateCodeResponseDto> getCouponStateCodes();
}
