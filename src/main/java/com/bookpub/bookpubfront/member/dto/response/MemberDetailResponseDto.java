package com.bookpub.bookpubfront.member.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 멤버에대한 상세 정보를 받는 메서드입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/

@Getter
@NoArgsConstructor
public class MemberDetailResponseDto {
    private Long memberNo;
    private String tierName;
    private String nickname;
    private String gender;
    private Integer birthMonth;
    private Integer birthYear;
    private String phone;
    private String email;
    private Long point;
    private String authority;
}