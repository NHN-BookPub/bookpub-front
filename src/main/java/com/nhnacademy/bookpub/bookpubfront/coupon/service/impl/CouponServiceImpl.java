package com.nhnacademy.bookpub.bookpubfront.coupon.service.impl;

import com.nhnacademy.bookpub.bookpubfront.coupon.adaptor.CouponAdaptor;
import com.nhnacademy.bookpub.bookpubfront.coupon.dto.request.CreateCouponRequestDto;
import com.nhnacademy.bookpub.bookpubfront.coupon.dto.response.GetCouponResponseDto;
import com.nhnacademy.bookpub.bookpubfront.coupon.service.CouponService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 쿠폰을 다루기 위한 서비스 구현체입니다.
 *
 * @author : 정유진, 김서현
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponAdaptor couponAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCoupon(CreateCouponRequestDto createRequestDto) {
        couponAdaptor.requestAddCoupon(createRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetCouponResponseDto> getCoupons(Pageable pageable, String searchKey,
            String search) {
        return couponAdaptor.requestCoupons(pageable, searchKey, search);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetCouponResponseDto> getPositiveCoupons(Pageable pageable, Long memberNo) {
        return couponAdaptor.requestPositiveCoupons(pageable, memberNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetCouponResponseDto> getNegativeCoupons(Pageable pageable, Long memberNo) {
        return couponAdaptor.requestNegativeCoupons(pageable, memberNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existCouponsByMemberNo(Long memberNo, List<Long> tierCoupons) {
        return couponAdaptor.requestExistCouponsByMemberNo(memberNo, tierCoupons);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void issueTierCoupons(Long memberNo, List<Long> tierCoupons) {
        couponAdaptor.requestIssueTierCoupons(memberNo, tierCoupons);
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public boolean checkCouponIssuedByMemberNo(Long memberNo, Long templateNo) {
        return couponAdaptor.requestExistMonthCoupon(memberNo, templateNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void issueMonthCoupon(Long memberNo, Long templateNo) {
        couponAdaptor.requestIssueMonthCoupon(memberNo, templateNo);
    }
}
