package com.nhnacademy.bookpub.bookpubfront.purchase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 구매이력 저장시 사용하는 dto class.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreatePurchaseRequestDto {
    private Long productNo;
    private Long purchasePrice;
    private Integer purchaseAmount;
    private Integer productType;
}
