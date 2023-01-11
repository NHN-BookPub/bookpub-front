package com.bookpub.bookpubfront.member.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 멤버 로그인 요청 dto.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class AuthMemberRequestDto {
    private String userId;
    private List<String> authorities;
}
