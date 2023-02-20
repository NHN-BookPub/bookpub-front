package com.nhnacademy.bookpub.bookpubfront.elastic.service;

import com.nhnacademy.bookpub.bookpubfront.elastic.dto.response.AllSearchResponseDto;
import com.nhnacademy.bookpub.bookpubfront.elastic.dto.response.ProductSearchResultDto;
import java.util.List;
import java.util.Map;

/**
 * Elastic 검색 서비스 인터페이스.
 *
 * @author : 박경서
 * @since : 1.0
 **/
public interface ElasticService {

    /**
     * 상품 제목으로 검색하는 메서드.
     *
     * @param keyword 검색어
     * @return 검색 결과
     */
    List<ProductSearchResultDto> searchProduct(String keyword);

    /**
     * 통함 검색으로 상품, 공지사항, faq 조회하는 메서드.
     *
     * @param keyword 검색어
     * @return 상품, 공지사항, faq 검색 결과
     */
    Map<String, List<AllSearchResponseDto>> searchAll(String keyword);
}
