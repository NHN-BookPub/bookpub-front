package com.nhnacademy.bookpub.bookpubfront.order.dto.response;

import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductListForOrderResponseDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 주문 상세정보를 반환하기 위한 Dto.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class GetOrderDetailResponseDto {
    private Long orderNo;
    private Long memberNo;
    private List<GetProductListForOrderResponseDto> orderProducts = new ArrayList<>();
    private String orderState;
    private String buyerName;
    private String buyerNumber;
    private String recipientName;
    private String recipientNumber;
    private String addressBase;
    private String addressDetail;
    private LocalDateTime createdAt;
    private LocalDateTime receivedAt;
    private String invoiceNo;
    private boolean packaged;
    private Long packageAmount;
    private Long deliveryAmount;
    private String orderRequest;
    private Long pointAmount;
    private Long couponAmount;
    private Long totalAmount;
    private String orderName;
    private String orderId;

    public void setDeliveryAmountToZero() {
        this.deliveryAmount = 0L;
    }

    public void setPackageAmountToZero() {
        this.packageAmount = 0L;
    }
}
