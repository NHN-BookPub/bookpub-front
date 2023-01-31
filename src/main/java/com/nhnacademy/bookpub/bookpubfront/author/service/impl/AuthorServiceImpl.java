package com.nhnacademy.bookpub.bookpubfront.author.service.impl;

import com.nhnacademy.bookpub.bookpubfront.author.adaptor.AuthorAdaptor;
import com.nhnacademy.bookpub.bookpubfront.author.dto.request.AddAuthorRequestDto;
import com.nhnacademy.bookpub.bookpubfront.author.dto.request.ModifyAuthorRequestDto;
import com.nhnacademy.bookpub.bookpubfront.author.dto.response.GetAuthorResponseDto;
import com.nhnacademy.bookpub.bookpubfront.author.service.AuthorService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * AuthorService 구현체입니다.
 *
 * @author : 박경서, 정유진, 김서현
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorAdaptor authorAdaptor;

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<GetAuthorResponseDto> getAuthors(Pageable pageable) {
        return authorAdaptor.getAuthors(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAuthor(AddAuthorRequestDto request) {
        authorAdaptor.addAuthor(request);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyAuthor(ModifyAuthorRequestDto request) {
        authorAdaptor.modifyAuthor(request);

    }
}
