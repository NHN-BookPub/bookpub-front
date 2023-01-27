package com.bookpub.bookpubfront.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 멤버의 주소록을 반환해주는 responseDto입니다.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetAddressResponseDto {
    private boolean addressMemberBased;
    private String roadAddress;
    private String addressDetail;
}