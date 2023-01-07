package com.bookpub.bookpubfront.member.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
public class SignupMemberResponseDto {
    @NotBlank
    @Size(min = 2, max = 200)
    @Pattern(regexp = "^.*(?=.*[가-힣])(?=^.{2,200}).*$")
    private String name;

    @NotBlank
    @Size(min = 2, max = 8)
    @Pattern(regexp = "^.*(?=.*[a-z])(?=.*\\d)(?=^.{2,8}).*$")
    private String nickname;

    @NotBlank
    @Size(min = 6, max = 6)
    @Pattern(regexp = "^.*(?=.*\\d)(?=^.{6}).*$")
    private String birth;

    @NotBlank
    @Size(min = 2, max = 2)
    private String gender;

    @NotBlank
    @Size(min = 5, max = 20)
    @Pattern(regexp = "^.*(?=.*[a-z])(?=.*\\d)(?=^.{5,20}).*$")
    private String memberId;

    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^.*(?=^.{8,20}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).*$")
    private String pwd;

    @NotBlank
    @Size(min = 11, max = 11)
    @Pattern(regexp = "^.*(?=.*\\d)(?=^.{11}).*$")
    private String phone;

    @NotBlank
    private String email;

    @NotBlank
    private String address;

    @NotBlank
    private String detailAddress;

    public void setEncodePwd(String encodePwd) {
        this.pwd = encodePwd;
    }
}
