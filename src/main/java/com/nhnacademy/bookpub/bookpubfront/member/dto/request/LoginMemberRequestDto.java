package com.nhnacademy.bookpub.bookpubfront.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * shop 서버에 회원정보를 요청하는 DTO.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class LoginMemberRequestDto {
    private String id;
    private String pwd;
}
