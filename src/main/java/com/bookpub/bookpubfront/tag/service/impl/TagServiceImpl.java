package com.bookpub.bookpubfront.tag.service.impl;

import com.bookpub.bookpubfront.tag.adaptor.TagAdaptor;
import com.bookpub.bookpubfront.tag.dto.AddTagRequestDto;
import com.bookpub.bookpubfront.tag.dto.GetTagResponseDto;
import com.bookpub.bookpubfront.tag.dto.ModifyTagRequestDto;
import com.bookpub.bookpubfront.tag.service.TagService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * TagService 구현체.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagAdaptor tagAdaptor;

    /**
     * {@inheritDoc}
     *
     * @throws JsonProcessingException Json 파싱 중 발생하는 오류
     */
    @Override
    public List<GetTagResponseDto> findAllTags() throws JsonProcessingException {
        return tagAdaptor.getTags();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTag(AddTagRequestDto request) {
        tagAdaptor.addTag(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyTag(ModifyTagRequestDto request) {
        tagAdaptor.modifyTag(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTag(Integer tagNo) {
        tagAdaptor.deleteTag(tagNo);
    }
}
