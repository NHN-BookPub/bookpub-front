package com.nhnacademy.bookpub.bookpubfront.member.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 멤버 리스트에 대한 일반정인 정보들이 반환되는 DTO 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private Long memberNo;
    private String tier;
    private String memberId;
    private String nickname;
    private String name;
    private String gender;
    private Integer birthYear;
    private Integer birthMonth;
    private String email;
    private Long point;
    private boolean isSocial;
    private boolean isDeleted;
    private boolean isBlocked;
}
