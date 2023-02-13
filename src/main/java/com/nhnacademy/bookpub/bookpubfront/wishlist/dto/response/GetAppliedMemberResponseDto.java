package com.nhnacademy.bookpub.bookpubfront.wishlist.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 위시리스트에 알림 여부를 등록한 사용자를 받는 dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
@ToString
public class GetAppliedMemberResponseDto {
    private Long memberNo;
    private String memberNickname;
    private String memberPhone;
    private Long productNo;
    private String title;
}
