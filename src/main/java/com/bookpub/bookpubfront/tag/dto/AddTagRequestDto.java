package com.bookpub.bookpubfront.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Some description here.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
@ToString
public class AddTagRequestDto {
    private String addTagName;
    private String addColorCode;
}
