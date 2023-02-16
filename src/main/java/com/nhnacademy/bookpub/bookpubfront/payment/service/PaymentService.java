package com.nhnacademy.bookpub.bookpubfront.payment.service;

import com.nhnacademy.bookpub.bookpubfront.payment.dto.request.OrderProductRefundRequestDto;
import com.nhnacademy.bookpub.bookpubfront.payment.dto.request.RefundRequestDto;
import javax.servlet.http.HttpServletResponse;

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
     * @param response 응답
     */
    void createPayment(String orderId, String paymentKey, Long amount,
                       HttpServletResponse response);

    /**
     * 주문 결제를 취소하는 메소드.
     *
     * @param refundRequestDto 환불요청 dto.
     */
    void refundOrder(RefundRequestDto refundRequestDto, Long memberNo);


    /**
     * 주문상품 결제를 취소하는 메소드.
     *
     * @param refundRequestDto 환불요청 dto.
     */
    void refundOrderProduct(OrderProductRefundRequestDto refundRequestDto, Long memberNo);

    /**
     * 주문상품을 교환하는 메소드.
     *
     * @param exchangeDto 교환신청 dto.
     */
    void exchangeOrderProduct(OrderProductRefundRequestDto exchangeDto, Long memberNo);
}
