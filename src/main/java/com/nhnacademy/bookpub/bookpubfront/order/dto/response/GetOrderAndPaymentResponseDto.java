package com.nhnacademy.bookpub.bookpubfront.order.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 주문, 결제 확인 정보 response dto.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderAndPaymentResponseDto {
    private String orderName;
    private String address;
    private String recipient;
    private LocalDateTime receiveDate;
    private Long totalAmount;
    private Long savePoint;
    private String cardCompany;
    private String receiptUrl;
}
