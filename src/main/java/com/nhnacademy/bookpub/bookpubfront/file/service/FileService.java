package com.nhnacademy.bookpub.bookpubfront.file.service;

import com.nhnacademy.bookpub.bookpubfront.file.dto.response.GetDownloadInfo;

/**
 * File 관련 서비스 인터페이스.
 *
 * @author : 박경서
 * @since : 1.0
 **/
public interface FileService {

    /**
     * 쿠폰 템플릿 정보 호출 메서드.
     *
     * @param templateNo 템플릿 번호
     * @return 템플릿 정보
     */
    GetDownloadInfo downloadCouponTemplateInfo(Long templateNo);

    /**
     * E-Book 정보 호출 메서드.
     *
     * @param productNo 상품 번호
     * @param memberNo  멤버 번호
     * @return 상품 정보
     */
    GetDownloadInfo downloadEBookInfo(Long productNo, Long memberNo);

    /**
     * File Download 메서드.
     *
     * @param path  파일이 저장되어있는 경로
     * @param token 오브젝트 스토리지 토큰
     * @return byte 단위 파일
     */
    byte[] downloadFile(String path, String token);
}
