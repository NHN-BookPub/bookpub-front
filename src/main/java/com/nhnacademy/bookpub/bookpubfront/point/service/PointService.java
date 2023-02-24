package com.nhnacademy.bookpub.bookpubfront.point.service;

import com.nhnacademy.bookpub.bookpubfront.point.dto.request.PointGiftRequestDto;
import com.nhnacademy.bookpub.bookpubfront.point.dto.response.GetPointAdminResponseDto;
import com.nhnacademy.bookpub.bookpubfront.point.dto.response.GetPointResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.time.LocalDateTime;
import org.springframework.data.domain.Pageable;

/**
 * 포인트 서비스.
 *
 * @author : 임태원
 * @since : 1.0
 **/
public interface PointService {
    /**
     * 회원의 포인트 내역을 타입에 맞게 조회해오는 메소드.
     * 타입 : 전체, 획득, 사용
     *
     * @param pageable 페이지
     * @param memberNo 회원번호.
     * @param type 타입
     * @return 회원 포인트 내역
     */
    PageResponse<GetPointResponseDto> getMemberPointHistory(
            Pageable pageable, Long memberNo, String type);

    /**
     * 포인트 선물하기.
     *
     * @param memberNo 회원번호.
     * @param requestDto 요청 dto.
     */
    void giftPoint(Long memberNo, PointGiftRequestDto requestDto);

    /**
     * 포인트 내역보기
     *
     * @param pageable 페이징값 반환
     * @param start    시작일자
     * @param end      종료일자
     * @return 포인트 내역 반환.
     */
    PageResponse<GetPointAdminResponseDto> getPoints(Pageable pageable, LocalDateTime start,
                                                     LocalDateTime end);

    /**
     * 관리자가 포인트내역을 회원 아이디를 검색하여 조회하기위한 메서드입니다.
     *
     * @param pageable 페이징
     * @param start 시작일
     * @param end 종료일
     * @param key 아이디 검색어
     * @return 포인트 내역
     */
    PageResponse<GetPointAdminResponseDto> getPointsBySearch(Pageable pageable,
                                                             LocalDateTime start,
                                                             LocalDateTime end,
                                                             String key);
}
