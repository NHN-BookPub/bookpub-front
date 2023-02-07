package com.nhnacademy.bookpub.bookpubfront.wishlist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 위시리시트 등록을 위한 dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreateWishlistRequestDto {
    private Long productNo;
}
