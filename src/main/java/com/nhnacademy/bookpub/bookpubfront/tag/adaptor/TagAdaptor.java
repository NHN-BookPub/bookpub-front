package com.nhnacademy.bookpub.bookpubfront.tag.adaptor;

import com.nhnacademy.bookpub.bookpubfront.tag.dto.AddTagRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tag.dto.GetTagResponseDto;
import com.nhnacademy.bookpub.bookpubfront.tag.dto.ModifyTagRequestDto;
import java.util.List;

/**
 * API 서버와 연동을 위한 인터페이스.
 *
 * @author : 박경서
 * @since : 1.0
 */
public interface TagAdaptor {
    /**
     * 전체 태그를 API 서버에서 받아오는 메서드.
     *
     * @return 전체 태그
     */
    List<GetTagResponseDto> getTags();

    /**
     * 태그 등록 API 를 보내는 메서드.
     *
     * @param request 등록을 위한 DTO
     */
    void addTag(AddTagRequestDto request);


    /**
     * 태그 수정 API 를 보내는 메서드.
     *
     * @param request 수정을 위한 DTO
     */
    void modifyTag(ModifyTagRequestDto request);


    /**
     * 태그 삭제 API 를 보내는 메서드.
     *
     * @param tagNo 삭제할 태그 번호
     */
    void deleteTag(Integer tagNo);
}
