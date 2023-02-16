package com.nhnacademy.bookpub.bookpubfront.member.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

/**
 * oauth member Request DTO개체.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class OauthMemberCreateRequestDto {
    @NotBlank
    @Pattern(regexp = "^[가-힣a-z]{2,200}$",
            message = "이름은 한글 또는 영어 2글자 이상 200글자 이하로 입력해주세요.")
    private String name;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\d]{2,8}$",
            message = "닉네임은 영어나 숫자로 2글자 이상 8글자 이하로 입력해주세요.")
    private String nickname;

    @NotBlank
    @Pattern(regexp = "^\\d{6}$", message = "생년월일은 숫자로 6글자 입력해주세요")
    private String birth;

    @NotBlank
    @Length(min = 2, max = 2, message = "성별의 길이는 2글자로 입력해주세요")
    private String gender;
    private String memberId;
    private String pwd;

    @NotBlank
    @Pattern(regexp = "^.*(?=.*\\d)(?=.{11}).*$", message = "전화번호는 숫자 11글자로 입력해주세요.")
    private String phone;

    @NotBlank
    @Pattern(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")
    private String email;

    @NotBlank
    private String address;
    @NotBlank
    private String detailAddress;

    /**
     * 비밀번호를 인코딩한 값으로 세팅해주는 메소드.
     *
     * @param encodePwd 암호화된 비번.
     */
    public void setEncodePwd(String encodePwd) {
        this.pwd = encodePwd;
    }
}
