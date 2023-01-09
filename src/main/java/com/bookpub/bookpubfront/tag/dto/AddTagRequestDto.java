package com.bookpub.bookpubfront.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 태그 등록을 위한 DTO.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class AddTagRequestDto {
    private String addTagName;
    private String addColorCode;
}
