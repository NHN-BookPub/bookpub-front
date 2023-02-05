package com.nhnacademy.bookpub.bookpubfront.payment.service;

/**
 * 결제 서비스.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public interface PaymentService {
    void successPayment(String orderId, String paymentKey, Long amount);
}
