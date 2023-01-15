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

    /** 회원가입 페이지를 연결해주는 메소드.
     *
     * @return 회원가입 페이지 view를 보여줌
     */
    @GetMapping("/signup")
    public String signupPageForm() {
        return "member/signupPage";
    }

    /** 회원가입 정보로 통신한 후 성공페이지를 띄워주는 메소드.
     *
     * @param signupMemberRequestDto 회원가입 정보를 담고있다.
     * @param model html에 동적인 정보를 전달해주는 객체.
     * @return 성공, 실패 페이지를 보여준다.
     */
    @PostMapping("/signup")
    public String signupComplete(@Valid SignupMemberRequestDto signupMemberRequestDto,
                                 Model model) {
        SignupMemberResponseDto memberInfo
                = memberService.signup(signupMemberRequestDto);

        model.addAttribute("member", memberInfo);

        return "member/signupComplete";
    }

    /** 로그인 화면을 보여주는 메소드.
     *
     * @param model html에 동적인 정보를 전달해주는 객체.
     * @param request 페이지의 요청정보가 담겨있는 객체.
     * @return 로그인 화면.
     */
    @GetMapping("/login")
    public String loginPageForm(Model model, HttpServletRequest request) {
        model.addAttribute("sessionId",request.getSession().getId());

        return "member/loginPage";
    }

    /** 로그인 성공, 실패에 따른 화면을 보여주는 view.
     *
     * @param requestDto 로그인 요청 정보가 담겨있는 dto.
     * @param request 페이지의 요청정보가 담겨있는 객체.
     * @return 메인화면 또는 로그인화면을 띄워준다.
     */
    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute LoginMemberRequestDto requestDto, HttpServletRequest request) {
        memberService.login(requestDto, request.getSession());

        return "redirect:/";
    }
}
