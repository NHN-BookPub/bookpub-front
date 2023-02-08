package com.nhnacademy.bookpub.bookpubfront.payment.service.impl;

import com.nhnacademy.bookpub.bookpubfront.payment.adaptor.PaymentAdaptor;
import com.nhnacademy.bookpub.bookpubfront.payment.exception.NotNormalPaymentException;
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


    /**
     * {@inheritDoc}
     */
    @Override
    public void verifyPayment(String orderId, Long amount) {
        boolean verifyResult = paymentAdaptor.verifyPayment(orderId, amount);

        if (!verifyResult) {
            throw new NotNormalPaymentException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPayment(String orderId, String paymentKey, Long amount) {
        paymentAdaptor.createPayment(orderId, paymentKey, amount);
    }
}
