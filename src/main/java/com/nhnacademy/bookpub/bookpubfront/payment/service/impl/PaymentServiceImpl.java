package com.nhnacademy.bookpub.bookpubfront.payment.service.impl;

import com.nhnacademy.bookpub.bookpubfront.payment.adaptor.PaymentAdaptor;
import com.nhnacademy.bookpub.bookpubfront.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 결제 서비스 구현체.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentAdaptor paymentAdaptor;

    @Override
    public void successPayment(String orderId, String paymentKey, Long amount) {
        paymentAdaptor.successPayment(orderId, paymentKey, amount);
    }
}
