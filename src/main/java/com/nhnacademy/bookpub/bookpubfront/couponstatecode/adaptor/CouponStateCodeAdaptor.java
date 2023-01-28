package com.nhnacademy.bookpub.bookpubfront.couponstatecode.adaptor;

import com.nhnacademy.bookpub.bookpubfront.couponstatecode.dto.response.GetCouponStateCodeResponseDto;
import java.util.List;

/**
 * api를 이용해 back 서버(shop)와 데이터를 주고받기 위해 만든 어댑터 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface CouponStateCodeAdaptor {
    /**
     * 쿠폰상태코드 리스트 조회를 위한 메소드입니다.
     *
     * @return 쿠폰상태코드 리스트
     */
    List<GetCouponStateCodeResponseDto> requestCouponStateCodes();
}

