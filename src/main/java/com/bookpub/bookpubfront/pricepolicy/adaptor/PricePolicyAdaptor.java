package com.bookpub.bookpubfront.pricepolicy.adaptor;

import com.bookpub.bookpubfront.pricepolicy.dto.CreatePricePolicyRequestDto;
import com.bookpub.bookpubfront.pricepolicy.dto.GetPricePolicyResponseDto;
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
     * @param codeNo 번호
     * @return 가격정책을 반환합니다.
     */
    GetPricePolicyResponseDto getPricePolicyByCodeNoRequest(Integer codeNo);

    /**
     * 가격정책을 등록합니다.
     *
     * @param requestDto dto
     */
    void createOrderStateCodeRequest(
            CreatePricePolicyRequestDto requestDto);

    /**
     * 가격정책의 가격을 수정합니다.
     *
     * @param codeNo 번호
     * @param fee 수정할 가격
     */
    void modifyPricePolicyFeeUsedRequest(Integer codeNo, Long fee);
}
