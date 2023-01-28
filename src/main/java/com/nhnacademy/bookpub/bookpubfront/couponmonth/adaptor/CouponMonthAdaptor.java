package com.nhnacademy.bookpub.bookpubfront.couponmonth.adaptor;

import com.nhnacademy.bookpub.bookpubfront.couponmonth.dto.request.CreateCouponMonthRequestDto;
import com.nhnacademy.bookpub.bookpubfront.couponmonth.dto.request.ModifyCouponMonthRequestDto;
import com.nhnacademy.bookpub.bookpubfront.couponmonth.dto.response.GetCouponMonthResponseDto;
import java.util.List;

/**
 * api를 이용해 back 서버(shop)와 데이터를 주고받기 위해 만든 어댑터 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface CouponMonthAdaptor {

    /**
     * 이달의 쿠폰 리스트를 조회하기 위한 메서드입니다.
     *
     * @return 이달의 쿠폰 리스트
     */
    List<GetCouponMonthResponseDto> requestCouponMonths();

    /**
     * 이달의 쿠폰을 등록하기 위한 메서드입니다.
     *
     * @param createRequestDto 이달의 쿠폰 등록에 필요한 정보를 담은 Dto
     */
    void requestAddCouponMonth(CreateCouponMonthRequestDto createRequestDto);

    /**
     * 이달의 쿠폰을 수정하기 위한 메서드입니다.
     *
     * @param modifyRequestDto 이달의 쿠폰 수정에 필요한 정보를 담은 Dto.
     */
    void requestModifyCouponMonth(ModifyCouponMonthRequestDto modifyRequestDto);

    /**
     * 이달의 쿠폰을 삭제하기 위한 메서드입니다.
     *
     * @param monthNo 이달의 쿠폰 번호
     */
    void requestDeleteCouponMonth(Long monthNo);
}
