package com.nhnacademy.bookpub.bookpubfront.oauth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * oauth 지원 서버에 저장되어 있는 로그인한 유저 정보.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GitHubUserResponseDto {
    private String login;
    private String name;
    private String email;
}
