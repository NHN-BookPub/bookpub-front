package com.nhnacademy.bookpub.bookpubfront.order.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 관리자용 주문리스트 조회 dto 입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class GetOrderListForAdminResponseDto {
    private Long orderNo;
    private String memberId;
    private LocalDateTime createdAt;
    private String invoiceNo;
    private String orderState;
    private Long totalAmount;
    private LocalDateTime receivedAt;
}
