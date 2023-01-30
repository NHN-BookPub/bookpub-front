package com.nhnacademy.bookpub.bookpubfront.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * shop 서버에서 응답받은 데이터가 들어갈 DTO
 *
 * @author : 임태원
 * @since : 1.0
 **/

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupMemberResponseDto {
    private String memberId;
    private String memberNickname;
    private String memberEmail;
    private String tierName;
}
