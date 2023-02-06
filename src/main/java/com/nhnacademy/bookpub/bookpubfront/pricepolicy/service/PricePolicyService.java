package com.nhnacademy.bookpub.bookpubfront.pricepolicy.service;

import com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.request.CreatePricePolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.response.GetOrderPolicyResponseDto;
import com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.response.GetPricePolicyResponseDto;
import java.util.List;

/**
 * 가격정책 어답터와 연결되는 서비스입니다.
 *
 * @author : 여운석, 임태원
 * @since : 1.0
 **/
public interface PricePolicyService {
    /**
     * 가격정책을 등록합니다.
     *
     * @param requestDto 등록을 위한 Dto.
     */
    void createPricePolicy(CreatePricePolicyRequestDto requestDto);

    /**
     * 모든 가격정책들을 반환합니다.
     *
     * @return 가격정책 리스트.
     */
    List<GetPricePolicyResponseDto> getPricePolicies();

    /**
     * 정책명으로 조회합니다.
     *
     * @param name 정책명.
     * @return 정책리스트.
     */
    List<GetPricePolicyResponseDto> getPricePoliciesByName(String name);

    /**
     * 주문서에 필요한 배송비, 포장비 정책을 가져오는 메소드입니다..
     *
     * @return 배송비, 포장비 정책.
     */
    List<GetOrderPolicyResponseDto> getOrderRequestPolicy();
}
