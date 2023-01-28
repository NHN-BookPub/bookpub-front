package com.nhnacademy.bookpub.bookpubfront.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 이메일을 수정하기위한 값이 들어오는 DTO 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class ModifyMemberEmailRequestDto {
    private String email;
}
