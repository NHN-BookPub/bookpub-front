package com.nhnacademy.bookpub.bookpubfront.wishlist.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 위시리스트 정보를 받기위한 Dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@NoArgsConstructor
public class GetWishlistResponseDto {
    private Long productNo;
    private String title;
    private String productPublisher;
    private String thumbnail;
    private String codeCategory;
    private boolean wishlistApplied;
}
