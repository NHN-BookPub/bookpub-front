package com.nhnacademy.bookpub.bookpubfront.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 회원의 비밀번호를 수정하기위한 메서드입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class ModifyMemberPasswordRequestDto {
    private String password;
}
