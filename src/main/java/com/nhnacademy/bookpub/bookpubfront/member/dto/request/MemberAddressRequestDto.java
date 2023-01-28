package com.nhnacademy.bookpub.bookpubfront.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 회원의 주소지를 추가할때 쓰이는 메서드입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class MemberAddressRequestDto {
    private String address;
    private String addressDetail;
}
