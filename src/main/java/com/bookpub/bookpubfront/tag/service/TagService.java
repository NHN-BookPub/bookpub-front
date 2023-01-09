package com.bookpub.bookpubfront.tag.service;

import com.bookpub.bookpubfront.tag.dto.AddTagRequestDto;
import com.bookpub.bookpubfront.tag.dto.GetTagResponseDto;
import com.bookpub.bookpubfront.tag.dto.ModifyTagRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

/**
 * 태그를 다루기 위한 서비스 인터페이스.
 *
 * @author : 박경서
 * @since : 1.0
 **/
public interface TagService {

    /**
     * 전체 태그들을 찾는 메서드.
     *
     * @return 전체 태그들
     * @throws JsonProcessingException Json 파싱 중 발생하는 오류
     */
    List<GetTagResponseDto> findAllTags() throws JsonProcessingException;

    /**
     * 태그를 등록하는 메서드.
     *
     * @param request 등록을 위한 DTO
     */
    void addTag(AddTagRequestDto request);

    /**
     * 태그를 수정하는 메서드.
     *
     * @para request 수정을 위한 DTO
     */
    void modifyTag(ModifyTagRequestDto request);

    /**
     * 태그를 삭제하는 메서드.
     *
     * @param tagNo 삭제할 태그 번호
     */
    void deleteTag(Integer tagNo);
}
