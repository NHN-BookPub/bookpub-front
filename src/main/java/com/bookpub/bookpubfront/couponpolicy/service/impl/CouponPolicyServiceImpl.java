package com.bookpub.bookpubfront.couponpolicy.service.impl;

import com.bookpub.bookpubfront.couponpolicy.adaptor.CouponPolicyAdaptor;
import com.bookpub.bookpubfront.couponpolicy.dto.request.CreateCouponPolicyRequestDto;
import com.bookpub.bookpubfront.couponpolicy.dto.request.ModifyCouponPolicyRequestDto;
import com.bookpub.bookpubfront.couponpolicy.dto.response.GetCouponPolicyResponseDto;
import com.bookpub.bookpubfront.couponpolicy.service.CouponPolicyService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    public void createCouponPolicy(CreateCouponPolicyRequestDto createRequestDto)
            throws JsonProcessingException {
        couponPolicyAdaptor.requestAddCouponPolicy(createRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyCouponPolicy(ModifyCouponPolicyRequestDto modifyRequestDto)
            throws JsonProcessingException {
        couponPolicyAdaptor.requestModifyCouponPolicy(modifyRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetCouponPolicyResponseDto> getCouponPolicies() throws JsonProcessingException {
        return couponPolicyAdaptor.requestCouponPolicies();
    }
}
