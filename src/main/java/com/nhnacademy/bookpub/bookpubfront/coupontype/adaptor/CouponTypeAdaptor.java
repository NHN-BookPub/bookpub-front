package com.nhnacademy.bookpub.bookpubfront.coupontype.adaptor;

import com.nhnacademy.bookpub.bookpubfront.coupontype.dto.response.GetCouponTypeResponseDto;
import java.util.List;

/**
 * api를 이용해 back 서버(shop)와 데이터를 주고받기 위해 만든 어댑터 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface CouponTypeAdaptor {

    /**
     * 쿠폰유형 리스트를 조회하기 위한 메소드입니다.
     *
     * @return 쿠폰유형 리스트
     */
    List<GetCouponTypeResponseDto> requestCouponTypes();
}
