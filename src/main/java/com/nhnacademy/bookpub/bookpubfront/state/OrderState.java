package com.nhnacademy.bookpub.bookpubfront.state;

import lombok.Getter;

/**
 * 주문상태를 관리하기위한 enum 입니다.
 * 주문상태는 결제대기,결제완료,결제취소,배송중,배송완료,배송취소 가 있습니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
public enum OrderState implements States {
    WAITING_PAYMENT("결제대기", true, "주문서 작성"),

    COMPLETE_PAYMENT("결제완료", true, "금액 지불"),
    WAITING_DELIVERY("배송준비", true, "배송 준비"),
    SHIPPING_DELIVERY("배송중", true, "상품 배송"),
    COMPLETE_DELIVERY("배송완료", true, "상품 배송 완료"),
    CANCEL_DELIVERY("배송취소", true, "상품 배송 취소"),
    CANCEL_PAYMENT("결제취소", true, "결제 취소"),
    CONFIRMED("구매확정", true, "구매 확정 상태");


    private final String name;
    private final boolean isUsed;
    private final String info;

    OrderState(String name, boolean isUsed, String info) {
        this.name = name;
        this.isUsed = isUsed;
        this.info = info;
    }
}
