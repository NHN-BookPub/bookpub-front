package com.bookpub.bookpubfront.member.dto.response;

import java.util.Set;
import lombok.Getter;

/**
 * shop 서버에서 응답받는 dto.
 *
 * @author : 임태원
 * @since : 1.0
 **/

@Getter
public class LoginMemberResponseDto {
    private String memberId;
    private Set<String> memberAuthorities;
}
