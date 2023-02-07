package com.nhnacademy.bookpub.bookpubfront.wishlist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 위시리스트 수정을 위한 dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class ModifyWishlistAlarmRequestDto {
    private Long productNo;
}
