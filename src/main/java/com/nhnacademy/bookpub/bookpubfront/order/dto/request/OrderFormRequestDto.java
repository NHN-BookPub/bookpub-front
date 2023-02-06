package com.nhnacademy.bookpub.bookpubfront.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 주문서에서 받아 올 정보 dto.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class OrderFormRequestDto {
    private Integer deliveryFeePolicyNo;
    private Integer packingFeeFeePolicyNo;
    private String buyerName;
    private String buyerNumber;
    private String recipientName;
    private String recipientNumber;
    private String roadAddress;
    private String addressDetail;
    private String receivedAt;
    private Boolean packaged;
    private String orderRequest;
    private Long pointAmount;
    private Long couponAmount;
    private Long totalAmount;
    private Long savePoint;
    private String orderName;
}
