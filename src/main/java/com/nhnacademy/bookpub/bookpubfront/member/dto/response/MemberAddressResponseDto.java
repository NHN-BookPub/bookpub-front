package com.nhnacademy.bookpub.bookpubfront.member.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 멤버의 주소정보를 받기위한 클래스입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class MemberAddressResponseDto {
    private Long addressNo;
    private String roadAddress;
    private String addressDetail;
    private boolean addressBased;
}
