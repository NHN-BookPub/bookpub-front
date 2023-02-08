package com.nhnacademy.bookpub.bookpubfront.payment.service;

/**
 * 결제 서비스.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public interface PaymentService {
    /**
     * 결제를 진행해도 되는 검증된 주문인지 확인하는 작업입니다.
     *
     * @param orderId 주문 id
     * @param amount 주문 금액
     */
    void verifyPayment(String orderId, Long amount);

    /**
     * 결제를 생성하는 메소드.
     *
     * @param orderId 주문 id
     * @param paymentKey 결제 키
     * @param amount 주문 금액
     */
    void createPayment(String orderId, String paymentKey, Long amount);
}
