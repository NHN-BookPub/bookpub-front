package com.nhnacademy.bookpub.bookpubfront.point.adaptor;

import com.nhnacademy.bookpub.bookpubfront.point.dto.request.PointGiftRequestDto;
import com.nhnacademy.bookpub.bookpubfront.point.dto.response.GetPointResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 * 포인트 어댑터.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public interface PointAdaptor {
    /**
     * 포인트내역 조회 메소드.
     *
     * @param pageable 페이지.
     * @param memberNo 멤버번호.
     * @param type     유형.
     * @return 포인트내역.
     */
    PageResponse<GetPointResponseDto> getMemberPointHistory(
            Pageable pageable, Long memberNo, String type);

    /**
     * 포인트 선물 메소드.
     *
     * @param requestDto 포인트 선물 요청 dto.
     */
    void giftPoint(PointGiftRequestDto requestDto);
}
