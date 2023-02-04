package com.nhnacademy.bookpub.bookpubfront.tiercoupon.service.impl;

import com.nhnacademy.bookpub.bookpubfront.tiercoupon.adaptor.TierCouponAdaptor;
import com.nhnacademy.bookpub.bookpubfront.tiercoupon.dto.request.CreateTierCouponRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tiercoupon.dto.response.GetTierCouponResponseDto;
import com.nhnacademy.bookpub.bookpubfront.tiercoupon.service.TierCouponService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 등급 쿠폰을 다루기 위한 구현체입니다.
 *
 * @author : 김서현
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class TierCouponServiceImpl implements TierCouponService {

    private final TierCouponAdaptor tierCouponAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetTierCouponResponseDto> getTierCoupons(Pageable pageable) {
        return tierCouponAdaptor.requestTierCoupons(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createTierCoupon(CreateTierCouponRequestDto createRequestDto) {
        tierCouponAdaptor.requestAddTierCoupon(createRequestDto);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTierCoupon(Integer tierNo, Long templateNo) {
        tierCouponAdaptor.requestDeleteTierCoupon(tierNo, templateNo);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Long> getTierCouponsByTierNo(Integer tierNo) {
        return tierCouponAdaptor.requestTierCouponsByTierNo(tierNo);
    }
}
