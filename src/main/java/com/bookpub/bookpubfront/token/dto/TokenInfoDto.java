package com.bookpub.bookpubfront.token.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Some description here
 *
 * @author : 임태원
 * @since : 1.0
 **/
@AllArgsConstructor
@Getter
public class TokenInfoDto {
    private String sub;
    private String memberUUID;
    private String roles;
    private String iat;
    private String exp;
}
