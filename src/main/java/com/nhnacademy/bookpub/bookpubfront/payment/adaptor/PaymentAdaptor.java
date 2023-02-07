package com.nhnacademy.bookpub.bookpubfront.payment.adaptor;

/**
 * 결제 어댑터.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public interface PaymentAdaptor {
    boolean verifyPayment(String orderId, Long amount);

    void createPayment(String orderId, String paymentKey, Long amount);
}
