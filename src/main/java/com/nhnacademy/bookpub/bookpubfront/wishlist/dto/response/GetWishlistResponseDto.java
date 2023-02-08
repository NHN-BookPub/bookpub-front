package com.nhnacademy.bookpub.bookpubfront.wishlist.dto.response;

import lombok.Getter;
import lombok.ToString;

/**
 * 위시리스트 정보를 담는 dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@ToString
public class GetWishlistResponseDto {
    private Long productNo;
    private String title;
    private String productPublisher;
    private String thumbnail;
    private boolean wishlistApplied;
}
