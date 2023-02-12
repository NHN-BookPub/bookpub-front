package com.nhnacademy.bookpub.bookpubfront.payment.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 주문 취소사유가 들어있는 dto.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class RefundRequestDto {
    @NotNull
    private Long orderNo;

    @NotNull
    @Size(min = 1, max = 200)
    private String cancelReason;
}
