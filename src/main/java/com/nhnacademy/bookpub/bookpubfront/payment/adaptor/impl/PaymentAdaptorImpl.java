package com.nhnacademy.bookpub.bookpubfront.payment.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig.getGatewayUrl;
import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;

import com.nhnacademy.bookpub.bookpubfront.payment.adaptor.PaymentAdaptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 결제 어댑터 구현체.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentAdaptorImpl implements PaymentAdaptor {
    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<Void> successPayment(String orderId, String paymentKey, Long amount) {
        return restTemplate.exchange(
                getGatewayUrl() + "/api/payment",
                HttpMethod.POST,
                new HttpEntity<>(makeHeader()),
                Void.class
        );
    }
}
