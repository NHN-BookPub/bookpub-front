package com.nhnacademy.bookpub.bookpubfront.order.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 결제 전 주문내용 확인을 위한 정보가 담겨있는 dto.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GetOrderConfirmResponseDto {
    private String orderName;
    private String buyerName;
    private String recipientName;
    private String addressBase;
    private String addressDetail;
    private LocalDateTime receivedAt;
    private String orderRequest;
    private Long totalAmount;
    private String orderId;
    private String orderState;
}
