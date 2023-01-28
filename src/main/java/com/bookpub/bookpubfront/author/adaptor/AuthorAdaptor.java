package com.bookpub.bookpubfront.author.adaptor;

import com.bookpub.bookpubfront.author.dto.response.GetAuthorResponseDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 * API 서버와 연동을 위한 저자 어댑터 인터페이스.
 *
 * @author : 박경서, 정유현
 * @since : 1.0
 **/
public interface AuthorAdaptor {

    /**
     * 페이징 정보를 가지고 작가들을 조회하는 메서드.
     *
     * @param pageable 페이징 정보
     * @return 페이징 별 모든 작가들 정보
     */
    PageResponse<GetAuthorResponseDto> getAuthors(Pageable pageable);
}