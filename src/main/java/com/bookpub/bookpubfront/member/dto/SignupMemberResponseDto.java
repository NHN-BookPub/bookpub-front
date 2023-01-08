package com.bookpub.bookpubfront.member.dto;

import lombok.Getter;

/**
 * shop 서버에서 응답받은 데이터가 들어갈 DTO
 *
 * @author : 임태원
 * @since : 1.0
 **/

@Getter
public class SignupMemberResponseDto {
    private String memberId;
    private String memberNickname;
    private String memberEmail;
    private String tierName;
}
