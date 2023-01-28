package com.nhnacademy.bookpub.bookpubfront.member.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 토큰을 들고가 유저의 정보를 가져온 response dto.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginResponseDto {
    private Long memberNo;
    private String memberPwd;
    private List<String> authorities;
}
