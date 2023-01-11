package com.bookpub.bookpubfront.tier.service;

import com.bookpub.bookpubfront.tier.dto.request.CreateTierRequestDto;
import com.bookpub.bookpubfront.tier.dto.request.ModifyTierRequestDto;
import com.bookpub.bookpubfront.tier.dto.response.TierResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

/**
 * 등급을 다루기위한 Service 레이어의 인터페이스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
public interface TierService {
    void createTier(CreateTierRequestDto createTierRequestDto) throws JsonProcessingException;

    void modifyTier(ModifyTierRequestDto modifyTierRequestDto) throws JsonProcessingException;

    List<TierResponseDto> getTiers();

    TierResponseDto getTier(Integer tierNo);
}
