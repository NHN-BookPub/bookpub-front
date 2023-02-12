package com.nhnacademy.bookpub.bookpubfront.payment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 토스 결제서비스 사용시 필요한 정보를 모아놓은 dto.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@AllArgsConstructor
@Getter
public class TossProviderDto {
    private String clientId;
    private String secret;
    private String successUrl;
    private String failUrl;
}
