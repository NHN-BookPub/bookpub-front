package com.nhnacademy.bookpub.bookpubfront.payment.exception;

/**
 * 정상적으로 결제가 생성되지 않았을 경우 발생하는 에러.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public class NotNormalPaymentException extends RuntimeException {
    public static final String MESSAGE = "결제가 정상적으로 생성되지 않았습니다";

    public NotNormalPaymentException() {
        super(MESSAGE);
    }
}
