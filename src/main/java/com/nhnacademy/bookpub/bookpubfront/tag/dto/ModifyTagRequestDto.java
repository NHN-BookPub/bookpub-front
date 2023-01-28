package com.nhnacademy.bookpub.bookpubfront.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 태그 수정을 위한 DTO.
 *
 * @author : 박경서
 * @since : 1.0
 **/

@Getter
@AllArgsConstructor
public class ModifyTagRequestDto {
    private Integer modifyTagNo;
    private String modifyTagName;
    private String modifyColorCode;
}
