package com.nhnacademy.bookpub.bookpubfront.handler.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 에러를 핸들링하기위한 클래스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@ToString
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private String code;
}
