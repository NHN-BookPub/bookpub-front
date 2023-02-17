package com.nhnacademy.bookpub.bookpubfront.handler.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 에러를 잡기위한 클래스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private String message;
}