package com.bookpub.bookpubfront.pricepolicy.service;

import com.bookpub.bookpubfront.pricepolicy.dto.CreatePricePolicyRequestDto;
import com.bookpub.bookpubfront.pricepolicy.dto.GetPricePolicyResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

/**
 * 가격정책 어답터와 연결되는 서비스입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
public interface PricePolicyService {
    /**
     * 가격정책을 등록합니다.
     *
     * @param requestDto 등록을 위한 Dto.
     * @throws JsonProcessingException Json 매핑시 가능한 예외.
     */
    void createPricePolicy(CreatePricePolicyRequestDto requestDto) throws JsonProcessingException;

    /**
     * 가격정책의 가격을 수정합니다.
     *
     * @param policyNo 정책번호.
     * @param fee 수정할 가격.
     */
    void modifyPricePolicyFee(Integer policyNo, Long fee);

    /**
     * 번호로 가격정책을 조회합니다.
     *
     * @param policyNo 정책번호.
     * @return 찾은 정책을 반환합니다.
     */
    GetPricePolicyResponseDto getPricePolicyByNo(Integer policyNo);

    /**
     * 모든 가격정책들을 반환합니다.
     *
     * @return 가격정책 리스트.
     */
    List<GetPricePolicyResponseDto> getPricePolicies();
}
