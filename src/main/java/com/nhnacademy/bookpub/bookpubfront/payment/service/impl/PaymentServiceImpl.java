package com.nhnacademy.bookpub.bookpubfront.payment.service.impl;

import com.nhnacademy.bookpub.bookpubfront.cart.util.CartUtils;
import com.nhnacademy.bookpub.bookpubfront.payment.adaptor.PaymentAdaptor;
import com.nhnacademy.bookpub.bookpubfront.payment.dto.request.OrderProductRefundRequestDto;
import com.nhnacademy.bookpub.bookpubfront.payment.dto.request.RefundRequestDto;
import com.nhnacademy.bookpub.bookpubfront.payment.exception.NotNormalPaymentException;
import com.nhnacademy.bookpub.bookpubfront.payment.service.PaymentService;
import com.nhnacademy.bookpub.bookpubfront.utils.CookieUtil;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 결제 서비스 구현체.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentAdaptor paymentAdaptor;
    private final RedisTemplate<String, String> redisTemplate;


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
    public void createPayment(String orderId, String paymentKey,
                              Long amount, HttpServletResponse response) {
        paymentAdaptor.createPayment(orderId, paymentKey, amount);
        deleteCookie(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refundOrder(RefundRequestDto refundRequestDto, Long memberNo) {
        paymentAdaptor.refundOrder(refundRequestDto, memberNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refundOrderProduct(OrderProductRefundRequestDto refundRequestDto, Long memberNo) {
        paymentAdaptor.refundOrderProduct(refundRequestDto, memberNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exchangeOrderProduct(OrderProductRefundRequestDto exchangeDto, Long memberNo) {
        paymentAdaptor.exchangeOrderProduct(exchangeDto, memberNo);
    }

    /**
     * 주문에 사용한 쿠키 삭제.
     *
     * @param response 응답.
     */
    private void deleteCookie(HttpServletResponse response) {
        Cookie cartCookie = CookieUtil.findCookie(CartUtils.CART_COOKIE);
        Cookie orderCookie = CookieUtil.findCookie(CartUtils.ORDER_INFO);

        if (Objects.isNull(cartCookie) || Objects.isNull(orderCookie)) {
            return;
        }

        String cartRedisKey = cartCookie.getValue();
        Arrays.stream(orderCookie.getValue().split("/"))
                .collect(Collectors.toList()).forEach(info ->
                        redisTemplate.opsForSet().remove(cartRedisKey, info.split("-")[0]));

        orderCookie.setMaxAge(0);
        orderCookie.setPath("/");
        response.addCookie(orderCookie);
    }
}
