package com.nhnacademy.bookpub.bookpubfront.oauth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * oauth 계정으로 회원가입 되어있는지 확인하기 위해 사용하는 requestDto 입니다.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OauthMemberRequestDto {
    private String id;
    private String pwd;
}
