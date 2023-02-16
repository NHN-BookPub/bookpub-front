package com.nhnacademy.bookpub.bookpubfront.payment.adaptor;

import com.nhnacademy.bookpub.bookpubfront.payment.dto.request.OrderProductRefundRequestDto;
import com.nhnacademy.bookpub.bookpubfront.payment.dto.request.RefundRequestDto;

/**
 * 결제 어댑터.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public interface PaymentAdaptor {
    /**
     * 유효한 주문인지 검증하는 메소드.
     *
     * @param orderId 주문 id
     * @param amount 주문 금액
     * @return 유효한 주문인지 아닌지
     */
    boolean verifyPayment(String orderId, Long amount);

    /**
     * 결제를 생성하는 메소드.
     *
     * @param orderId 주문 id
     * @param paymentKey 결제 고유 키
     * @param amount 주문 금액
     */
    void createPayment(String orderId, String paymentKey, Long amount);

    /**
     * 주문 결제를 취소하는 메소드.
     *
     * @param refundRequestDto 환불요청 dto.
     */
    void refundOrder(RefundRequestDto refundRequestDto, Long memberNo);

    /**
     * 주문 상품 결제를 취소하는 메소드.
     *
     * @param refundRequestDto 환불요청 dto.
     */
    void refundOrderProduct(OrderProductRefundRequestDto refundRequestDto, Long memberNo);

    /**
     * 주문 상품 교환하는 메소드.
     *
     * @param exchangeDto 환불요청 dto.
     */
    void exchangeOrderProduct(OrderProductRefundRequestDto exchangeDto, Long memberNo);
}
