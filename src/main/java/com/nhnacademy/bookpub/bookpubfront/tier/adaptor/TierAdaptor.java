package com.nhnacademy.bookpub.bookpubfront.tier.adaptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.bookpub.bookpubfront.tier.dto.request.CreateTierRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tier.dto.request.ModifyTierRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tier.dto.response.TierResponseDto;
import java.util.List;

/**
 * Tier 가 api 서버완 연동하기위한 인터페이스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/

public interface TierAdaptor {
    void requestAddTier(CreateTierRequestDto createRequestDto) throws JsonProcessingException;

    void requestModifyTier(ModifyTierRequestDto modifyTierRequestDto) throws JsonProcessingException;

    List<TierResponseDto> requestTierList();

    TierResponseDto requestTier(Integer tierNo);
}
