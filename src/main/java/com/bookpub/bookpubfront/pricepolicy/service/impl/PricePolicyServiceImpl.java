package com.bookpub.bookpubfront.pricepolicy.service.impl;

import com.bookpub.bookpubfront.pricepolicy.adaptor.PricePolicyAdaptor;
import com.bookpub.bookpubfront.pricepolicy.dto.CreatePricePolicyRequestDto;
import com.bookpub.bookpubfront.pricepolicy.dto.GetPricePolicyResponseDto;
import com.bookpub.bookpubfront.pricepolicy.service.PricePolicyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 가격정책 서비스의 구현체입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class PricePolicyServiceImpl implements PricePolicyService {
    private final PricePolicyAdaptor pricePolicyAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPricePolicy(CreatePricePolicyRequestDto requestDto) throws JsonProcessingException {
        pricePolicyAdaptor.createOrderStateCodeRequest(requestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyPricePolicyFee(Integer policyNo, Long fee) {
        pricePolicyAdaptor.modifyPricePolicyFeeUsedRequest(policyNo, fee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetPricePolicyResponseDto getPricePolicyByNo(Integer policyNo) {
        return pricePolicyAdaptor.getPricePolicyByCodeNoRequest(policyNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetPricePolicyResponseDto> getPricePolicies() {
        return pricePolicyAdaptor.getAllPricePolicyCodeRequest();
    }
}
