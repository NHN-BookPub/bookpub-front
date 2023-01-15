package com.bookpub.bookpubfront.member.controller;

import com.bookpub.bookpubfront.member.dto.request.LoginMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.bookpub.bookpubfront.member.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 멤버 api 컨트롤러.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
@Slf4j
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
    public String loginPageForm(Model model, HttpServletRequest request) {
        model.addAttribute("sessionId",request.getSession().getId());

        return "member/loginPage";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute LoginMemberRequestDto requestDto, HttpServletRequest request) {
        memberService.login(requestDto, request.getSession());

        return "redirect:/";
    }
}
