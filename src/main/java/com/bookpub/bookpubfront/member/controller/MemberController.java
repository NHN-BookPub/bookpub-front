package com.bookpub.bookpubfront.member.controller;

import com.bookpub.bookpubfront.member.dto.request.LoginMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberResponseDto;
import com.bookpub.bookpubfront.member.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import javax.validation.Valid;
import com.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 멤버를 다루는 멤버 컨트롤러입니다.
 *
 * @author : 임태원, 유호철
 * @since : 1.0
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /** 회원가입 페이지를 연결해주는 메소드.
     *
     * @return 회원가입 페이지 view를 보여줌
     */
    @GetMapping("/signup")
    public String signupPageForm() {
        return "member/signupPage";
    /**
     * 관리자가 멤버 정보들을 볼수있는 View 로 갑니다.
     *
     * @param pageable 페이징 정보기입.
     * @param model    모델.
     * @return 관리자 멤버 리스트 페이지.
     */
    @GetMapping("/admin/members")
    public String memberList(@PageableDefault Pageable pageable,
                              Model model) {
        PageResponse<MemberResponseDto> members = memberService.getMembers(pageable);

        model.addAttribute("content", members.getContent());
        model.addAttribute("next", members.isNext());
        model.addAttribute("previous", members.isPrevious());
        model.addAttribute("totalPage", members.getTotalPages());
        model.addAttribute("pageNum", members.getNumber());
        model.addAttribute("previousPageNo", members.getNumber() - 1);
        model.addAttribute("nextPageNo", members.getNumber() + 1);
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("uri", "/admin/members");

        return "admin/member/memberList";
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
    /**
     * 관리자 멤버의 세부정보를 파악하기위한 메서드입니다.
     *
     * @param memberNo 멤버번호.
     * @param model    모델.
     * @return 관리자 의 멤버 상세페이지.
     */
    @GetMapping("/admin/members/{memberNo}")
    public String adminMemberInfo(@PathVariable("memberNo") Long memberNo,
                                  Model model) {
        MemberDetailResponseDto member = memberService.getMember(memberNo);

        model.addAttribute("member", member);

        return "admin/member/memberInfo";
    }

    /**
     * 관리자가 사용자를 차단하기위해 쓰이는 컨트롤러.
     *
     * @param memberNo 멤버번호.
     * @param pageable 페이징 정보.
     * @return 멤버리스트 페이지로 리다이렉트.
     */
    @PostMapping("/admin/members/{memberNo}")
    public String adminMemberBlock(@PathVariable("memberNo") Long memberNo,
                                   Pageable pageable) {
        memberService.memberBlock(memberNo);
        return "redirect:/admin/members?page=" + pageable.getPageNumber() + "?size=" + pageable.getPageSize();
    }

    /**
     * 멤버가 멤버 정보를 보기위한 요청.
     *
     * @param memberNo 멤버 정보가 기입
     * @param model 모델.
     * @return 멤버의 개인정보 페이지로 이동.
     */
    @GetMapping("/members/{memberNo}")
    public String memberInfo(@PathVariable("memberNo") Long memberNo,
                             Model model) {
        MemberDetailResponseDto member = memberService.getMember(memberNo);
        model.addAttribute("member", member);

        return "members/memberInfo";
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
