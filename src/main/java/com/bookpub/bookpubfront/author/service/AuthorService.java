package com.bookpub.bookpubfront.author.service;

import com.bookpub.bookpubfront.author.dto.response.GetAuthorResponseDto;
import com.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 * 저자를 다루기 위한 서비스 인터페이스.
 *
 * @author : 박경서, 정유진
 * @since : 1.0
 **/
public interface AuthorService {

    /**
     * 전체 저자를 부르기 위한 서비스 메서드.
     *
     * @param pageable 페이징 정보
     * @return 저자 응답 DTO
     */
    PageResponse<GetAuthorResponseDto> getAuthors(Pageable pageable);
}
