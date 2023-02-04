package com.nhnacademy.bookpub.bookpubfront.order.dto.response;

import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductListForOrderResponseDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 주문 목록을 위한 Dto.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class GetOrderListResponseDto {
    private Long orderNo;
    private List<GetProductListForOrderResponseDto> orderProducts = new ArrayList<>();
    private String orderState;
    private LocalDateTime createdAt;
    private LocalDateTime receivedAt;
    private String invoiceNo;
    private Long totalAmount;
}
