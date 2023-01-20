package com.bookpub.bookpubfront.pricepolicy.adaptor;

import com.bookpub.bookpubfront.pricepolicy.dto.CreatePricePolicyRequestDto;
import com.bookpub.bookpubfront.pricepolicy.dto.GetPricePolicyResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

/**
 * 가격정책 api 와 연동하기 위한 adaptor 입니다.
 *
 * @author : 여운석
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
     * 번호로 가격정책을 조회합니다.
     *
     * @param codeNo
     * @return
     */
    GetPricePolicyResponseDto getPricePolicyByCodeNoRequest(Integer codeNo);

    /**
     * 가격정책을 등록합니다.
     *
     * @param requestDto
     * @throws JsonProcessingException
     */
    void createOrderStateCodeRequest(
            CreatePricePolicyRequestDto requestDto)
            throws JsonProcessingException;

    /**
     * 가격정책의 가격을 수정합니다.
     *
     * @param codeNo
     * @param fee
     */
    void modifyPricePolicyFeeUsedRequest(Integer codeNo, Long fee);
}
