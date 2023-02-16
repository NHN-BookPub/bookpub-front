package com.nhnacademy.bookpub.bookpubfront.file.adaptor;

import com.nhnacademy.bookpub.bookpubfront.file.dto.response.GetDownloadInfo;

/**
 * File API 부르기 위한 어댑터.
 *
 * @author : 박경서
 * @since : 1.0
 **/
public interface FileAdaptor {

    /**
     * 쿠폰 템플릿 정보 조회 API 호출.
     *
     * @param templateNo 템플릿 번호
     * @return 템플릿 오브젝트 스토리지 정보
     */
    GetDownloadInfo requestCouponTemplateInfo(Long templateNo);

    /**
     * E-Book 정보 조회 API 호출.
     *
     * @param productNo 싱픔 반호
     * @param memberNo  멤버 번호
     * @return E-Book 오브젝트 스토리지 정보
     */
    GetDownloadInfo requestEBookInfo(Long productNo, Long memberNo);
}
