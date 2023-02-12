package com.nhnacademy.bookpub.bookpubfront.payment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 토스에서 전달받는 responseDto.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {
    private String paymentKey;
    private String orderId;
    private Long amount;
}
