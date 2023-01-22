package com.bookpub.bookpubfront.member.controller;

import com.bookpub.bookpubfront.config.GateWayConfig;
import com.bookpub.bookpubfront.member.dto.request.LoginMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.request.MemberAddressRequestDto;
import com.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberResponseDto;
import com.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.bookpub.bookpubfront.member.service.MemberService;
import com.bookpub.bookpubfront.token.util.JwtUtil;
import com.bookpub.bookpubfront.utils.PageResponse;
import java.time.Duration;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private static final  String REDIRECT_MY_PAGE = "redirect:/members/";
    private final MemberService memberService;

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

    /**
     * 회원가입 페이지를 연결해주는 메소드.
     *
     * @param model the model
     * @return 회원가입 페이지 view를 보여줌
     */
    @GetMapping("/signup")
    public String signupPageForm(Model model) {
        model.addAttribute("url", GateWayConfig.getGatewayUrl() + "/api/signup");
        return "member/signupPage";
    }

    /**
     * 회원가입 정보로 통신한 후 성공페이지를 띄워주는 메소드.
     *
     * @param signupMemberRequestDto 회원가입 정보를 담고있다.
     * @param model                  html에 동적인 정보를 전달해주는 객체.
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
     * @param model    모델.
     * @return 멤버의 개인정보 페이지로 이동.
     */
    @GetMapping("/members/{memberNo}")
    public String memberInfo(@PathVariable("memberNo") Long memberNo,
                             Model model) {
        MemberDetailResponseDto member = memberService.getMember(memberNo);
        model.addAttribute("member", member);

        return "mypage/memberInfo";
    }

    /**
     * 로그인 화면을 보여주는 메소드.
     *
     * @param model   html에 동적인 정보를 전달해주는 객체.
     * @param request 페이지의 요청정보가 담겨있는 객체.
     * @return 로그인 화면.
     */
    @GetMapping("/login")
    public String loginPageForm(Model model, HttpServletRequest request) {
        model.addAttribute("sessionId", request.getSession().getId());

        return "member/login";
    }

    /**
     * 로그인 성공, 실패에 따른 화면을 보여주는 view.
     *
     * @param requestDto 로그인 요청 정보가 담겨있는 dto.
     * @param request    페이지의 요청정보가 담겨있는 객체.
     * @param response   the response
     * @return 메인화면 또는 로그인화면을 띄워준다.
     */
    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute LoginMemberRequestDto requestDto,
                              HttpServletRequest request, HttpServletResponse response) {
        memberService.login(requestDto, request.getSession());

        Cookie cookie = new Cookie(JwtUtil.JWT_SESSION, request.getSession().getId());
        cookie.setMaxAge((int) Duration.ofDays(1).getSeconds());
        cookie.setPath("/");

        response.addCookie(cookie);

        return "redirect:/";
    }

    /**
     * 로그아웃
     *
     * @param request  HTTP Request.
     * @param response the response
     * @return 로그아웃 후 메인화면으로 리다이렉트.
     */
    @GetMapping("/logout")
    public String logoutSubmit(HttpServletRequest request, HttpServletResponse response) {
        memberService.logout(response, request.getSession());

        return "redirect:/";
    }

    /**
     * 회원의 이름을 바꾸는 메서드입니다.
     * 마이페이지 내정보로 리다이렉트 됩니다.
     *
     * @param memberNo 회원번호
     * @param name     변경할 이름
     * @return 마이페이지 내정보.
     */
    @PostMapping("/members/{memberNo}/name")
    public String memberExchangeName(@PathVariable("memberNo") Long memberNo,
                                      @RequestParam("exchangeName") String name) {
        memberService.modifyMemberName(memberNo, name);


        return REDIRECT_MY_PAGE + memberNo;
    }

    /**
     * 회원의 닉네임을 변경하기위한 메서드입니다.
     * 마이페이지의 내정보창으로 반환됩니다.
     *
     * @param memberNo 회원번호.
     * @param nickname the nickname
     * @return the string
     */
    @PostMapping("/members/{memberNo}/nickname")
    public String memberExchangeNickname(@PathVariable("memberNo") Long memberNo,
                                         @RequestParam("exchangeNickname") String nickname) {
        memberService.modifyMemberNickName(memberNo, nickname);

        return REDIRECT_MY_PAGE + memberNo;
    }

    /**
     * 회원의 이메일을 변경할때쓰이는 메서드입니다.
     * 마이페이지 내정보창으로 이동합니다.
     *
     * @param memberNo 회원번호
     * @param email    변경할 이메일.
     * @return 마이페이지 내정보.
     */
    @PostMapping("/members/{memberNo}/email")
    public String memberExchangeEmail(@PathVariable("memberNo") Long memberNo,
                                      @RequestParam("exchangeEmail") String email) {
        memberService.modifyMemberEmail(memberNo, email);

        return REDIRECT_MY_PAGE + memberNo;
    }

    /**
     * 회원의 휴대전화번호가 변경될때 사용되는 메서드입니다.
     * 마이페이의 내정보 페이지로 이동합니다.
     *
     * @param memberNo 회원번호
     * @param phone    휴대전화번호기입
     * @return 마이페이지 내정보
     */
    @PostMapping("/members/{memberNo}/phone")
    public String memberExchangePhone(@PathVariable("memberNo") Long memberNo,
                                      @RequestParam("exchangePhone") String phone) {
        memberService.modifyMemberPhone(memberNo, phone);

        return REDIRECT_MY_PAGE + memberNo;
    }

    /**
     * 멤버의 비밀번호가 변경될때 쓰이는 메서드입니다.
     * 마이페이지의 내정보 페이지로 이동합니다.
     *
     * @param memberNo 회원번호
     * @param password 변경할 비밀번호
     * @return the string
     */
    @PostMapping("/members/{memberNo}/password")
    public String memberExchangePassword(@PathVariable("memberNo") Long memberNo,
                                         @RequestParam("exchangePwd") String password){

        memberService.modifyMemberPassword(memberNo,password);
        return REDIRECT_MY_PAGE + memberNo;
    }


    /**
     * 멤버의 기준주소지를 변경하기위한 메서드입니다.
     *
     * @param memberNo  회원번호
     * @param addressNo 주소번호
     * @return the string
     */
    @PostMapping("/members/{memberNo}/addresses/{addressNo}")
    public String memberExchangeBaseAddress(@PathVariable("memberNo") Long memberNo,
                                            @PathVariable("addressNo") Long addressNo){

        memberService.modifyMemberAddress(memberNo, addressNo);
        return REDIRECT_MY_PAGE + memberNo;
    }

    /**
     * 회원의 주소를 추가하기위한 메서드입니다.
     *
     * @param memberNo   회원번호
     * @param requestDto 주소를 등록하기위한 정보.
     * @return the string
     */
    @PostMapping("/members/{memberNo}/addresses")
    public String memberAddAddress(@PathVariable("memberNo") Long memberNo,
                                   MemberAddressRequestDto requestDto){

        memberService.addMemberAddress(memberNo, requestDto);

        return REDIRECT_MY_PAGE + memberNo;
    }

    /**
     * 회원의 주소를 삭제하기위한 메서드입니다.
     *
     * @param memberNo  회원 번호 기입.
     * @param addressNo 삭제할 주소번호 기압.
     * @return the string
     */
    @PostMapping("/members/{memberNo}/addresses-delete/{addressNo}")
    public String memberDeleteAddress(@PathVariable("memberNo") Long memberNo,
                                      @PathVariable("addressNo") Long addressNo) {
        memberService.deleteMemberAddress(memberNo, addressNo);
        return REDIRECT_MY_PAGE + memberNo;
    }
}
