package com.nhnacademy.bookpub.bookpubfront.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 비회원 주문조회를 위한 dto.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class GetOrderDetailNonMemberRequestDto {
    String orderId;
    String phoneNo;
}
