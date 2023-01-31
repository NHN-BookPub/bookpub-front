package com.nhnacademy.bookpub.bookpubfront.author.service;

import com.nhnacademy.bookpub.bookpubfront.author.dto.request.AddAuthorRequestDto;
import com.nhnacademy.bookpub.bookpubfront.author.dto.request.ModifyAuthorRequestDto;
import com.nhnacademy.bookpub.bookpubfront.author.dto.response.GetAuthorResponseDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 * 작가를 다루기 위한 서비스 인터페이스.
 *
 * @author : 박경서, 정유진, 김서현
 * @since : 1.0
 **/
public interface AuthorService {

    /**
     * 전체 작가를 부르기 위한 서비스 메서드.
     *
     * @param pageable 페이징 정보
     * @return 저자 응답 DTO
     */
    PageResponse<GetAuthorResponseDto> getAuthors(Pageable pageable);

    /**
     * 작가를 등록하기 위한 서비스 메서드.
     *
     * @param request 저자 등록 DTO.
     */
    void addAuthor(AddAuthorRequestDto request);

    /**
     * 작가를 수정하기 위한 서비스 메서드.
     *
     * @param request 저자 수정 DTO.
     */
    void modifyAuthor(ModifyAuthorRequestDto request);
}
