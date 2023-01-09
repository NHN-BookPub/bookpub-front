package com.bookpub.bookpubfront.couponpolicy.adaptor;

import com.bookpub.bookpubfront.couponpolicy.dto.request.CreateCouponPolicyRequestDto;
import com.bookpub.bookpubfront.couponpolicy.dto.request.ModifyCouponPolicyRequestDto;
import com.bookpub.bookpubfront.couponpolicy.dto.response.GetCouponPolicyResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

/**
 * api를 이용해 back 서버(shop)와 데이터를 주고받기 위해 만든 어댑터 인터페이스입니다.
 *
 * @author : 정유진
 * @since : 1.0
 **/
public interface CouponPolicyAdaptor {


    /**
     * 쿠폰정책을 등록하기 위한 메소드입니다.
     *
     * @param createRequestDto 쿠폰정책 생성에 필요한 정보들을 담은 Dto
     * @throws JsonProcessingException json 관련 에러
     */
    void requestAddCouponPolicy(CreateCouponPolicyRequestDto createRequestDto)
            throws JsonProcessingException;

    /**
     * 쿠폰정책을 수정하기 위한 메소드입니다.
     *
     * @param modifyRequestDto 쿠폰정책 수정에 필요한 정보들을 담은 Dto.
     * @throws JsonProcessingException json 관련 에러
     */
    void requestModifyCouponPolicy(ModifyCouponPolicyRequestDto modifyRequestDto)
            throws JsonProcessingException;

    /**
     * 쿠폰정책 단건 조회를 위한 메소드입니다.
     *
     * @param policyNo 쿠폰정책번호
     * @return 쿠폰정책 정보들을 담은 GetCouponPolicyResponseDto
     */
    GetCouponPolicyResponseDto requestCouponPolicy(Integer policyNo);

    /**
     * 쿠폰정책 리스트 조회를 위한 메소드입니다.
     *
     * @return 쿠폰정책 정보들을 담은 GetCouponPolicyResponseDto 리스트
     * @throws JsonProcessingException json 관련 에러
     */
    List<GetCouponPolicyResponseDto> requestCouponPolicies() throws JsonProcessingException;
}
