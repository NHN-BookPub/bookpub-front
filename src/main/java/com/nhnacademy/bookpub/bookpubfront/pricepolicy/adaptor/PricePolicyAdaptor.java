package com.nhnacademy.bookpub.bookpubfront.pricepolicy.adaptor;

import com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.request.CreatePricePolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.response.GetOrderPolicyResponseDto;
import com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.response.GetPricePolicyResponseDto;
import java.util.List;

/**
 * 가격정책 api 와 연동하기 위한 adaptor 입니다.
 *
 * @author : 여운석, 임태원
 * @since : 1.0
 **/
public interface PricePolicyAdaptor {
    /**
     * 모든 가격정책을 반환합니다.
     *
     * @return 가격정책 리스트.
     */
    List<GetPricePolicyResponseDto> getAllPricePolicyCodeRequest();


    /**
     * 가격정책을 등록합니다.
     *
     * @param requestDto dto
     */
    void createOrderStateCodeRequest(CreatePricePolicyRequestDto requestDto);

    /**
     * 정책명으로 조회합니다.
     *
     * @param name 정책명.
     * @return 정책리스트.
     */
    List<GetPricePolicyResponseDto> getPoliciesByName(String name);

    /**
     * 포장비, 배송비의 가장 최근 정책을 요청합니다.
     *
     * @return 포장비, 배송비 정책
     */
    List<GetOrderPolicyResponseDto> getShipAndPackagePolicy();
}
