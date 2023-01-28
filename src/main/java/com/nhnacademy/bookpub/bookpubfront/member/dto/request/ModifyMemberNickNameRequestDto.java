package com.nhnacademy.bookpub.bookpubfront.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 멤버의 닉네임을 수정하기위한 클래스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/

@Getter
@AllArgsConstructor
public class ModifyMemberNickNameRequestDto {
    private String nickname;
}
