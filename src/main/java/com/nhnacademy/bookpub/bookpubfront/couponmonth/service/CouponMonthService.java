package com.nhnacademy.bookpub.bookpubfront.couponmonth.service;

import com.nhnacademy.bookpub.bookpubfront.couponmonth.dto.request.CreateCouponMonthRequestDto;
import com.nhnacademy.bookpub.bookpubfront.couponmonth.dto.request.ModifyCouponMonthRequestDto;
import com.nhnacademy.bookpub.bookpubfront.couponmonth.dto.response.GetCouponMonthResponseDto;
import java.util.List;

/**
 * 이달의 쿠폰을 다루기 위한 서비스 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface CouponMonthService {
    /**
     * 이달의 쿠폰 리스트 조회를 위한 메서드입니다.
     *
     * @return 이달의 쿠폰 리스트
     */
    List<GetCouponMonthResponseDto> getCouponMonths();

    /**
     * 이달의 쿠폰 생성을 위한 메서드입니다.
     *
     * @param createRequestDto 이달의 쿠폰 생성에 필요한 정보를 담은 Dto
     */
    void createCouponMonth(CreateCouponMonthRequestDto createRequestDto);

    /**
     * 이달의 쿠폰 수정을 위한 메서드입니다.
     *
     * @param modifyRequestDto 이달의 쿠폰 수정에 필요한 정보를 담은 Dto
     */
    void modifyCouponMonth(ModifyCouponMonthRequestDto modifyRequestDto);

    /**
     * 이달의 쿠폰 삭제를 위한 메서드입니다.
     *
     * @param monthNo 삭제할 이달의 쿠폰 번호
     */
    void deleteCouponMonth(Long monthNo);
}
