package com.nhnacademy.bookpub.bookpubfront.wishlist.service;

import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.request.CreateWishlistRequestDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.request.DeleteWishlistRequestDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.request.ModifyWishlistAlarmRequestDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.response.GetWishlistCountResponseDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.response.GetWishlistResponseDto;
import org.springframework.data.domain.Pageable;

/**
 * Wishlist Service 인터페이스.
 *
 * @author : 박경서
 * @since : 1.0
 **/
public interface WishlistService {

    /**
     * 위시리스트 생성 서비스 메서드.
     *
     * @param memberNo 멤버 번호
     * @param request  상품 번호를 담은 dto
     */
    void createWishlist(Long memberNo, CreateWishlistRequestDto request);

    /**
     * 위시리스트 조회하는 서비스 메서드.
     *
     * @param memberNo 멤버 번호
     * @param pageable 페이징 정보
     * @return 위시리스트 페이지 목록
     */
    PageResponse<GetWishlistResponseDto> getWishlistByMember(Long memberNo, Pageable pageable);

    /**
     * 위시리스트 삭제하는 서비스 메서드.
     *
     * @param memberNo 멤버 번호
     * @param request  상품 번호를 담은 dto
     */
    void deleteWishlist(Long memberNo, DeleteWishlistRequestDto request);

    /**
     * 위시리스트 알람 여부를 수정하는 서비스 메서드.
     *
     * @param memberNo 멤버 번호
     * @param request  상품 번호를 담은 dto
     */
    void modifyWishlistAlarm(Long memberNo, ModifyWishlistAlarmRequestDto request);


    /**
     * 관리자 좋아요 통계 조회하는 메서드.
     *
     * @param categoryNo 카테고리 번호
     * @param pageable   페이징 정보
     * @return 상품별 좋아요 카운트
     */
    PageResponse<GetWishlistCountResponseDto> getWishListCount(Integer categoryNo,
            Pageable pageable);
}
