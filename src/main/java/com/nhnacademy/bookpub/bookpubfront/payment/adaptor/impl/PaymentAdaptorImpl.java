package com.nhnacademy.bookpub.bookpubfront.payment.adaptor.impl;

import static com.nhnacademy.bookpub.bookpubfront.config.GateWayConfig.getGatewayUrl;
import static com.nhnacademy.bookpub.bookpubfront.utils.Utils.makeHeader;

import com.nhnacademy.bookpub.bookpubfront.payment.adaptor.PaymentAdaptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
    private static final String API_URL = "/api/payment";

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verifyPayment(String orderId, Long amount) {
        String url = UriComponentsBuilder.fromHttpUrl(getGatewayUrl() + API_URL + "/verify")
                .queryParam("orderId", orderId)
                .queryParam("amount", amount)
                .build().toUriString();

        return Boolean.TRUE.equals(restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(makeHeader()),
                Boolean.class
        ).getBody());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPayment(String orderId, String paymentKey, Long amount) {
        String url = UriComponentsBuilder.fromHttpUrl(getGatewayUrl() + API_URL)
                .queryParam("paymentKey", paymentKey)
                .queryParam("orderId", orderId)
                .queryParam("amount", amount)
                .build().toUriString();

        restTemplate.postForEntity(
                url,
                new HttpEntity<>(makeHeader()),
                Void.class
        );
    }
}
