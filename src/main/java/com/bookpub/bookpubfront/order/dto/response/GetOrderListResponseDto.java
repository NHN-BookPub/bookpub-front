package com.bookpub.bookpubfront.order.dto.response;

import com.bookpub.bookpubfront.product.dto.response.GetProductListForOrderResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

/**
 * 주문 목록을 위한 Dto.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
public class GetOrderListResponseDto {
    public GetOrderListResponseDto(Long orderNo,
                                   String orderState,
                                   LocalDateTime createdAt,
                                   LocalDateTime receivedAt,
                                   String invoiceNo,
                                   Long totalAmount) {
        this.orderNo = orderNo;
        this.orderState = orderState;
        this.createdAt = createdAt;
        this.receivedAt = receivedAt;
        this.invoiceNo = invoiceNo;
        this.totalAmount = totalAmount;
    }

    private Long orderNo;
    private List<GetProductListForOrderResponseDto> orderProducts;
    private String orderState;
    private LocalDateTime createdAt;
    private LocalDateTime receivedAt;
    private String invoiceNo;
    private Long totalAmount;

    public void addOrderProducts(List<GetProductListForOrderResponseDto> products) {
        this.orderProducts = products;
    }
}
