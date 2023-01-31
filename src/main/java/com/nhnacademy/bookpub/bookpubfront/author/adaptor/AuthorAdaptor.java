package com.nhnacademy.bookpub.bookpubfront.author.adaptor;

import com.nhnacademy.bookpub.bookpubfront.author.dto.request.AddAuthorRequestDto;
import com.nhnacademy.bookpub.bookpubfront.author.dto.request.ModifyAuthorRequestDto;
import com.nhnacademy.bookpub.bookpubfront.author.dto.response.GetAuthorResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
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

    /**
     * 저자 등록 API 를 보내는 메서드.
     *
     * @param request 등록을 위한 DTO.
     */
    void addAuthor(AddAuthorRequestDto request);

    /**
     * 저자 수정 API 를 보내는 메서드.
     *
     * @param request 수정을 위한 DTO.
     */
    void modifyAuthor(ModifyAuthorRequestDto request);

}
