package com.nhnacademy.bookpub.bookpubfront.pricepolicy.service.impl;

import com.nhnacademy.bookpub.bookpubfront.pricepolicy.adaptor.PricePolicyAdaptor;
import com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.request.CreatePricePolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.response.GetOrderPolicyResponseDto;
import com.nhnacademy.bookpub.bookpubfront.pricepolicy.dto.response.GetPricePolicyResponseDto;
import com.nhnacademy.bookpub.bookpubfront.pricepolicy.service.PricePolicyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 가격정책 서비스의 구현체입니다.
 *
 * @author : 여운석, 임태원
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
    public void createPricePolicy(CreatePricePolicyRequestDto requestDto) {
        pricePolicyAdaptor.createOrderStateCodeRequest(requestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetPricePolicyResponseDto> getPricePolicies() {
        return pricePolicyAdaptor.getAllPricePolicyCodeRequest();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetPricePolicyResponseDto> getPricePoliciesByName(String name) {
        return pricePolicyAdaptor.getPoliciesByName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GetOrderPolicyResponseDto> getOrderRequestPolicy() {
        return pricePolicyAdaptor.getShipAndPackagePolicy();
    }
}
