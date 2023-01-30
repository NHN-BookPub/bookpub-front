package com.nhnacademy.bookpub.bookpubfront.couponpolicy.service.impl;

import com.nhnacademy.bookpub.bookpubfront.couponpolicy.adaptor.CouponPolicyAdaptor;
import com.nhnacademy.bookpub.bookpubfront.couponpolicy.dto.request.CreateCouponPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.couponpolicy.dto.request.ModifyCouponPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.couponpolicy.dto.response.GetCouponPolicyResponseDto;
import com.nhnacademy.bookpub.bookpubfront.couponpolicy.service.CouponPolicyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 쿠폰정책을 다루기 위한 서비스 구현체입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class CouponPolicyServiceImpl implements CouponPolicyService {
    private final CouponPolicyAdaptor couponPolicyAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCouponPolicy(CreateCouponPolicyRequestDto createRequestDto) {
        couponPolicyAdaptor.requestAddCouponPolicy(createRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyCouponPolicy(ModifyCouponPolicyRequestDto modifyRequestDto) {
        couponPolicyAdaptor.requestModifyCouponPolicy(modifyRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetCouponPolicyResponseDto> getCouponPolicies() {
        return couponPolicyAdaptor.requestCouponPolicies();
    }
}
