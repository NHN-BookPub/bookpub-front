package com.nhnacademy.bookpub.bookpubfront.wishlist.adaptor;

import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.request.CreateWishlistRequestDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.request.DeleteWishlistRequestDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.request.ModifyWishlistAlarmRequestDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.response.GetWishlistCountResponseDto;
import com.nhnacademy.bookpub.bookpubfront.wishlist.dto.response.GetWishlistResponseDto;
import org.springframework.data.domain.Pageable;

/**
 * API 서버와 연결하기 위한 인터페이스.
 *
 * @author : 박경서
 * @since : 1.0
 **/
public interface WishlistAdaptor {

    /**
     * 위시리스트 생성을 위한 메서드.
     *
     * @param memberNo 멤버 번호
     * @param request  생성을 위한 dto
     */
    void requestCreateWishlist(Long memberNo, CreateWishlistRequestDto request);

    /**
     * 멤버별 위시리스트를 조회하는 메서드.
     *
     * @param memberNo 멤버 번호
     * @param pageable 페이징 정보
     * @return 위시리스트 정보들
     */
    PageResponse<GetWishlistResponseDto> requestWishlistsByMember(Long memberNo, Pageable pageable);

    /**
     * 위시리스트 삭제를 위한 메서드.
     *
     * @param memberNo 멤버 번호
     * @param request  삭제를 위한 dto
     */
    void requestDeleteWishlist(Long memberNo, DeleteWishlistRequestDto request);

    /**
     * 위시리스트 상품 알림 변경을 위한 메서드.
     *
     * @param memberNo 멤버 번호
     * @param request  수정을 위한 dto
     */
    void requestModifyWishlistAlarm(Long memberNo, ModifyWishlistAlarmRequestDto request);

    /**
     * 관리자 좋아요 통계를 조회하기 위한 메서드.
     *
     * @param categoryNo 카테고리 번호
     * @param pageable   페이징 정보
     * @return 상품별 좋아요 카운트
     */
    PageResponse<GetWishlistCountResponseDto> requestWishListCount(Integer categoryNo,
            Pageable pageable);
}
