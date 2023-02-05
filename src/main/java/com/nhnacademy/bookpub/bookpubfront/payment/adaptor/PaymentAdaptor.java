package com.nhnacademy.bookpub.bookpubfront.payment.adaptor;

import org.springframework.http.ResponseEntity;

/**
 * 결제 어댑터.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public interface PaymentAdaptor {
    ResponseEntity<Void> successPayment(String orderId, String paymentKey, Long amount);
}
