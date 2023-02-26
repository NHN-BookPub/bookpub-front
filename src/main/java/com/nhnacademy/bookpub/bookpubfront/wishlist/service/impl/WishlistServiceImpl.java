package com.nhnacademy.bookpub.bookpubfront.wishlist.service.impl;

import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.wishlist.adaptor.WishlistAdaptor;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.request.CreateWishlistRequestDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.request.DeleteWishlistRequestDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.request.ModifyWishlistAlarmRequestDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.response.GetWishlistCountResponseDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.response.GetWishlistResponseDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Wishlist Service 구현체.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistAdaptor wishlistAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createWishlist(Long memberNo, CreateWishlistRequestDto request) {
        wishlistAdaptor.requestCreateWishlist(memberNo, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetWishlistResponseDto> getWishlistByMember(Long memberNo,
            Pageable pageable) {
        return wishlistAdaptor.requestWishlistsByMember(memberNo, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteWishlist(Long memberNo, DeleteWishlistRequestDto request) {
        wishlistAdaptor.requestDeleteWishlist(memberNo, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyWishlistAlarm(Long memberNo, ModifyWishlistAlarmRequestDto request) {
        wishlistAdaptor.requestModifyWishlistAlarm(memberNo, request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetWishlistCountResponseDto> getWishListCount(Integer categoryNo,
            Pageable pageable) {
        return wishlistAdaptor.requestWishListCount(categoryNo, pageable);
    }
}
