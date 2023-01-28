package com.nhnacademy.bookpub.bookpubfront.tag.service.impl;

import com.nhnacademy.bookpub.bookpubfront.tag.adaptor.TagAdaptor;
import com.nhnacademy.bookpub.bookpubfront.tag.dto.AddTagRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tag.dto.GetTagResponseDto;
import com.nhnacademy.bookpub.bookpubfront.tag.dto.ModifyTagRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tag.service.TagService;
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
     */
    @Override
    public List<GetTagResponseDto> findAllTags()  {
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
