package com.nhnacademy.bookpub.bookpubfront.elastic.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 통합 검색 (상품, 공지사항, faq) 인덱스 조회 dto.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@Setter
public class AllResponseHit {
    private AllHit hits;
}
