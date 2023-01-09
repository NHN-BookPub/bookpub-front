package com.bookpub.bookpubfront.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Some description here.
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
