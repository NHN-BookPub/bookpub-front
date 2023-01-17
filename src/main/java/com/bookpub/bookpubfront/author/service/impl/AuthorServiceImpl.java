package com.bookpub.bookpubfront.author.service.impl;

import com.bookpub.bookpubfront.author.adaptor.AuthorAdaptor;
import com.bookpub.bookpubfront.author.dto.response.GetAuthorResponseDto;
import com.bookpub.bookpubfront.author.service.AuthorService;
import com.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * AuthorService 구현체입니다.
 *
 * @author : 박경서, 정유진
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
}
