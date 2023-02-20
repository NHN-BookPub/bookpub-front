package com.nhnacademy.bookpub.bookpubfront.elastic.adaptor;

/**
 * ElasticSearch 서버와 통신할 어댑터.
 *
 * @author : 박경서
 * @since : 1.0
 **/
public interface ElasticAdaptor {

    /**
     * 상품 제목 검색어에 대한 결과를 조회하는 API 호출.
     *
     * @param keyword 검색어
     * @return ElasticSearch 응답
     */
    String requestSearchProduct(String keyword);

    /**
     * 통함 검색어에 대한 결과를 조회하는 API 호출.
     *
     * @param keyword 검색어
     * @return ElasticSearch 응답
     */
    String requestSearchAll(String keyword);
}
