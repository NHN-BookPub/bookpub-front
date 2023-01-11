package com.bookpub.bookpubfront.member.controller;

import com.bookpub.bookpubfront.member.dto.request.AuthMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.request.LoginMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.response.AuthMemberResponseDto;
import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.bookpub.bookpubfront.member.service.MemberService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 멤버 api 컨트롤러.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/signup")
    public String signupPageForm() {
        return "member/signupPage";
    }

    @PostMapping("/signup")
    public String signupComplete(@Valid SignupMemberRequestDto signupMemberRequestDto,
                                 Model model) {
        SignupMemberResponseDto memberInfo
                = memberService.signup(signupMemberRequestDto);

        model.addAttribute("member", memberInfo);

        return "member/signupComplete";
    }

    @GetMapping("/login")
    public String loginPageForm() {
        return "member/loginPage";
    }

    @PostMapping("/login")
    public String login(
            @Valid @RequestBody LoginMemberRequestDto loginMemberRequestDto) {

        return null;
    }

    @GetMapping("/auth/test")
    public String test2(Model model) {
        List<String> authorities = new ArrayList<>();
        authorities.add("ROLE_MEMBER");

        AuthMemberRequestDto authMemberRequestDto = new AuthMemberRequestDto(
                "tagkdj1",
                authorities
        );

        AuthMemberResponseDto login = memberService.auth(authMemberRequestDto);

        model.addAttribute("token", login.getToken());

        return "member/test";
    }

}
