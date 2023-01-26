package com.bookpub.bookpubfront.order.dto.response;

import com.bookpub.bookpubfront.product.dto.response.GetProductListForOrderResponseDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * 주문 상세정보를 반환하기 위한 Dto.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
public class GetOrderDetailResponseDto {

    @Builder
    public GetOrderDetailResponseDto(Long orderNo,
                                     String orderState,
                                     String buyerName,
                                     String buyerNumber,
                                     String recipientName,
                                     String recipientNumber,
                                     String addressBase,
                                     String addressDetail,
                                     LocalDateTime createdAt,
                                     LocalDateTime receivedAt,
                                     String invoiceNo,
                                     boolean packaged,
                                     Long packageAmount,
                                     Long deliveryAmount,
                                     String orderRequest,
                                     Long pointAmount,
                                     Long couponAmount,
                                     Long totalAmount) {
        this.orderNo = orderNo;
        this.orderState = orderState;
        this.buyerName = buyerName;
        this.buyerNumber = buyerNumber;
        this.recipientName = recipientName;
        this.recipientNumber = recipientNumber;
        this.addressBase = addressBase;
        this.addressDetail = addressDetail;
        this.createdAt = createdAt;
        this.receivedAt = receivedAt;
        this.invoiceNo = invoiceNo;
        this.packaged = packaged;
        this.packageAmount = packageAmount;
        this.deliveryAmount = deliveryAmount;
        this.orderRequest = orderRequest;
        this.pointAmount = pointAmount;
        this.couponAmount = couponAmount;
        this.totalAmount = totalAmount;
    }

    private final Long orderNo;
    private List<GetProductListForOrderResponseDto> orderProducts = new ArrayList<>();
    private final String orderState;
    private final String buyerName;
    private final String buyerNumber;
    private final String recipientName;
    private final String recipientNumber;
    private final String addressBase;
    private final String addressDetail;
    private final LocalDateTime createdAt;
    private final LocalDateTime receivedAt;
    private final String invoiceNo;
    private final boolean packaged;
    private final Long packageAmount;
    private final Long deliveryAmount;
    private final String orderRequest;
    private final Long pointAmount;
    private final Long couponAmount;
    private final Long totalAmount;

    public void addProducts(List<GetProductListForOrderResponseDto> products) {
        this.orderProducts = products;
    }
}
