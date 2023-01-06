package com.bookpub.bookpubfront.member.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * member Request DTO개체
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class MemberRequest {
    @NotBlank
    @Size(min = 5, max = 20)
    private String ID;

    @NotBlank
    @Size(min = 8, max = 20)
    private String pwd;


}
