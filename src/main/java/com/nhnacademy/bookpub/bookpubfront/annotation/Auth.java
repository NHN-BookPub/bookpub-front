package com.nhnacademy.bookpub.bookpubfront.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 로그인 한 유저의 정보가 필요할 때 붙이는 어노테이션.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
}
