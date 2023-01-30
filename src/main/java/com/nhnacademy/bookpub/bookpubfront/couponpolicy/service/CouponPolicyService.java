package com.nhnacademy.bookpub.bookpubfront.couponpolicy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.bookpub.bookpubfront.couponpolicy.dto.request.CreateCouponPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.couponpolicy.dto.request.ModifyCouponPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.couponpolicy.dto.response.GetCouponPolicyResponseDto;
import java.util.List;

/**
 * 쿠폰정책을 다루기 위한 서비스 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface CouponPolicyService {
    /**
     * 쿠폰정책을 등록하기 위한 메소드입니다.
     *
     * @param createRequestDto 쿠폰등록에 필요한 정보들을 담은 Dto
     */
    void createCouponPolicy(CreateCouponPolicyRequestDto createRequestDto)
            throws JsonProcessingException;

    /**
     * 쿠폰정책을 수정하기 위한 메소드입니다.
     *
     * @param modifyRequestDto 쿠폰수정에 필요한 정보들을 담은 Dto
     */
    void modifyCouponPolicy(ModifyCouponPolicyRequestDto modifyRequestDto)
            throws JsonProcessingException;

    /**
     * 쿠폰정책 리스트를 조회하기 위한 메소드입니다.
     *
     * @return 쿠폰정책 정보들을 담은 GetCouponPolicyResponseDto 리스트
     */
    List<GetCouponPolicyResponseDto> getCouponPolicies();
}
