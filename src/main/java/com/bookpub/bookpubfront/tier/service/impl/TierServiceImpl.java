package com.bookpub.bookpubfront.tier.service.impl;

import com.bookpub.bookpubfront.tier.adaptor.TierAdaptor;
import com.bookpub.bookpubfront.tier.dto.request.CreateTierRequestDto;
import com.bookpub.bookpubfront.tier.dto.request.ModifyTierRequestDto;
import com.bookpub.bookpubfront.tier.dto.response.TierResponseDto;
import com.bookpub.bookpubfront.tier.service.TierService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 등급을 다루기위한 실클래스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class TierServiceImpl implements TierService {
    private final TierAdaptor tierAdapter;

    /**
     * {@inheritDoc}
     *
     * @throws JsonProcessingException objectMapper 를 통해 변환했을때 예외 발생가능.
     */
    @Override
    public void createTier(CreateTierRequestDto createTierRequestDto) throws JsonProcessingException {
        tierAdapter.requestAddTier(createTierRequestDto);
    }

    /**
     * {@inheritDoc}
     *
     * @throws JsonProcessingException objectMapper 를 통해 변환했을때 예외 발생가능.
     */
    @Override
    public void modifyTier(ModifyTierRequestDto modifyTierRequestDto) throws JsonProcessingException {
        tierAdapter.requestModifyTier(modifyTierRequestDto);

    }

    /**
     * {@inheritDoc}
     *
     * @throws JsonProcessingException objectMapper 를 통해 변환했을때 예외 발생가능.
     */
    @Override
    public List<TierResponseDto> getTiers() {
        return tierAdapter.requestTierList();
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public TierResponseDto getTier(Integer tierNo){
        return tierAdapter.requestTier(tierNo);
    }
}
