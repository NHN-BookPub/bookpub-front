package com.nhnacademy.bookpub.bookpubfront.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 회원가입 아이디 체크 request Dto.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class IdCheckRequestDto {
    private String id;
}
