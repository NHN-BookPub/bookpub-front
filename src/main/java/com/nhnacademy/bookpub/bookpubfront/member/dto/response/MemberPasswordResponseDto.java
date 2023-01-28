package com.nhnacademy.bookpub.bookpubfront.member.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 멤버의 encoding 된 정보를 받기위한 DTO 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class MemberPasswordResponseDto {
    private String password;
}
