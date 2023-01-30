package com.nhnacademy.bookpub.bookpubfront.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 요청마다 SecurityContextHolder에 올라갈 dto 객체.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthDto {
    private String memberNo;
    private String memberPwd;
    private List<String> authorities;
}
