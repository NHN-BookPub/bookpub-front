package com.bookpub.bookpubfront.member.controller;

import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.member.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 멤버 api 컨트롤러.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class SignUpController {
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

}
