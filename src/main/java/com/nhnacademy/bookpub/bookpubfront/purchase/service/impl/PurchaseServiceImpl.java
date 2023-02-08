package com.nhnacademy.bookpub.bookpubfront.purchase.service.impl;

import com.nhnacademy.bookpub.bookpubfront.purchase.adaptor.PurchaseAdaptor;
import com.nhnacademy.bookpub.bookpubfront.purchase.dto.request.CreatePurchaseRequestDto;
import com.nhnacademy.bookpub.bookpubfront.purchase.dto.response.GetPurchaseListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.purchase.service.PurchaseService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 구매이력 서비스의 구현체입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseAdaptor purchaseAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetPurchaseListResponseDto> getPurchases(Pageable pageable) {
        return purchaseAdaptor.getPurchases(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPurchase(CreatePurchaseRequestDto request) {
        purchaseAdaptor.createPurchase(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetPurchaseListResponseDto> getPurchasesByProductNo(
            Long productNo, Pageable pageable) {
        return purchaseAdaptor.getPurchasesByProductNo(productNo, pageable);
    }
}
