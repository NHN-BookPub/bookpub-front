package com.nhnacademy.bookpub.bookpubfront.purchase.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 매입이력 조회시 사용되는 dto class.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class GetPurchaseListResponseDto {
    private Long productNo;
    private String productTitle;
    private Long purchaseNo;
    private Integer purchaseAmount;
    private Long purchasePrice;
    private LocalDateTime createdAt;
}
