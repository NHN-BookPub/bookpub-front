package com.nhnacademy.bookpub.bookpubfront.member.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.cart.util.CartUtils;
import com.nhnacademy.bookpub.bookpubfront.category.util.CategoryUtils;
import com.nhnacademy.bookpub.bookpubfront.coupon.dto.response.GetCouponResponseDto;
import com.nhnacademy.bookpub.bookpubfront.coupon.service.CouponService;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.MemberAddressRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.OauthMemberCreateRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.request.SignupMemberRequestDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.dto.response.SignupMemberResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.service.MemberService;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import com.nhnacademy.bookpub.bookpubfront.oauth.dto.request.OauthMemberRequestDto;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
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

    private static final String REDIRECT_MY_PAGE = "redirect:/members";
    private static final String MEMBER = "member";
    private static final String AUTH_MEMBER = "oauthMember";
    private final MemberService memberService;
    private final MemberUtils memberUtils;
    private final CategoryUtils categoryUtils;
    private final CouponService couponService;
    private final CartUtils cartUtils;


    /**
     * 관리자가 멤버 정보들을 볼수있는 View 로 갑니다.
     *
     * @param pageable 페이징 정보기입.
     * @param model    모델.
     * @return 관리자 멤버 리스트 페이지.
     */
    @GetMapping("/admin/members")
    @Auth
    public String memberList(@PageableDefault Pageable pageable,
                             Model model,
                             @RequestParam(required = false) String nick,
                             @RequestParam(required = false) String id) {
        PageResponse<MemberResponseDto> members;

        if (nick != null) {
            members = memberService.getMembersByNickName(pageable, nick);
            model.addAttribute("uri", "/admin/members?nick=" + nick);
        } else if (id != null) {
            members = memberService.getMembersById(pageable, id);
            model.addAttribute("uri", "/admin/members?id=" + id);
        } else {
            members = memberService.getMembers(pageable);
            model.addAttribute("uri", "/admin/members");
        }

        model.addAttribute("content", members.getContent());
        model.addAttribute("next", members.isNext());
        model.addAttribute("previous", members.isPrevious());
        model.addAttribute("totalPage", members.getTotalPages());
        model.addAttribute("pageNum", members.getNumber());
        model.addAttribute("previousPageNo", members.getNumber() - 1);
        model.addAttribute("nextPageNo", members.getNumber() + 1);
        model.addAttribute("size", pageable.getPageSize());

        return "admin/member/memberList";
    }

    /**
     * 회원가입 페이지를 연결해주는 메소드.
     *
     * @return 회원가입 페이지 view를 보여줌
     */
    @GetMapping("/signup")
    public String signupPageForm() {
        return "member/signupPage";
    }

    /**
     * oauth 회원가입 페이지를 연결해주는 메소드.
     *
     * @param model   model.
     * @param request 요청.
     * @return oauth 회원의 가입 페이지.
     */
    @GetMapping("/oauth/signup")
    public String oauthSignupPageForm(Model model, HttpServletRequest request) {
        OauthMemberRequestDto oauthMember
                = (OauthMemberRequestDto) request.getAttribute(AUTH_MEMBER);

        model.addAttribute(AUTH_MEMBER, oauthMember);
        return "member/oauthSignupPage";
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
                                 Model model, @CookieValue(name = CartUtils.CART_COOKIE) String key) {
        SignupMemberResponseDto memberInfo
                = memberService.signup(signupMemberRequestDto);

        cartUtils.getCountInCart(key, model);
        memberUtils.modelRequestMemberNo(model);
        categoryUtils.categoriesView(model);
        categoryUtils.categoriesView(model);
        model.addAttribute(MEMBER, memberInfo);

        return "member/signupComplete";
    }

    /**
     * 회원가입 정보로 통신한 후 성공페이지를 띄워주는 메소드.
     *
     * @param signupMemberRequestDto 회원가입 정보를 담고있다.
     * @param model                  html에 동적인 정보를 전달해주는 객체.
     * @return 성공, 실패 페이지를 보여준다.
     */
    @PostMapping("/oauth/signup")
    public String oauthSignupComplete(@Valid OauthMemberCreateRequestDto signupMemberRequestDto,
                                      Model model) {
        SignupMemberResponseDto memberInfo
                = memberService.signup(signupMemberRequestDto);

        model.addAttribute(MEMBER, memberInfo);

        return "member/signupComplete";
    }

    /**
     * 관리자가 사용자를 차단하기위해 쓰이는 컨트롤러.
     *
     * @param memberNo 멤버번호.
     * @param pageable 페이징 정보.
     * @return 멤버리스트 페이지로 리다이렉트.
     */
    @PostMapping("/admin/members/{memberNo}")
    @Auth
    public String adminMemberBlock(@PathVariable("memberNo") Long memberNo,
                                   Pageable pageable) {
        memberService.memberBlock(memberNo);
        return "redirect:/admin/members?page="
                + pageable.getPageNumber() + "?size=" + pageable.getPageSize();
    }

    /**
     * 멤버가 멤버 정보를 보기위한 요청.
     *
     * @param model 모델.
     * @return 멤버의 개인정보 페이지로 이동.
     */
    @GetMapping("/members")
    @Auth
    public String memberInfo(Model model) {
        Long memberNo = memberUtils.getMemberNo();
        MemberDetailResponseDto member = memberService.getTokenMember(memberNo);

        String birthYear = String.valueOf(member.getBirthYear());
        if (birthYear.length() == 1) {
            birthYear = "0" + birthYear;
        }
        String birthMonth = String.valueOf(member.getBirthMonth());
        if (birthMonth.length() == 3) {
            birthMonth = "0" + birthMonth;
        }

        model.addAttribute("member", member);
        model.addAttribute("birthYear", birthYear);
        model.addAttribute("birthMonth", birthMonth);

        return "mypage/memberInfo";
    }

    /**
     * 멤버의 사용가능한 쿠폰 리스트를 조회하기 위한 요청.
     *
     * @param pageable 페이징 정보
     * @param model    모델
     * @return 멤버의 사용 가능 쿠폰함 페이지로 이동.
     */
    @GetMapping("/members/coupon/positive")
    @Auth
    public String memberCouponList(@PageableDefault Pageable pageable, Model model) {
        Long memberNo = memberUtils.getMemberNo();
        memberUtils.modelRequestMemberNo(model);
        PageResponse<GetCouponResponseDto> positiveCoupons = couponService.getPositiveCoupons(
                pageable, memberNo);

        model.addAttribute("positiveList", positiveCoupons.getContent());
        model.addAttribute("positiveTotalPages", positiveCoupons.getTotalPages());
        model.addAttribute("positiveCurrentPage", positiveCoupons.getNumber());
        model.addAttribute("positiveIsNext", positiveCoupons.isNext());
        model.addAttribute("positiveIsPrevious", positiveCoupons.isPrevious());
        model.addAttribute("positivePageButtonNum", 5);

        return "mypage/mycouponPositive";
    }

    /**
     * 멤버의 사용 불가능 쿠폰 리스트를 조회하기 위한 요청.
     *
     * @param pageable 페이징 정보
     * @param model    모델
     * @return 멤버의 사용 불가능 쿠폰함 페이지로 이동.
     */
    @Auth
    @GetMapping("/members/coupon/negative")
    public String memberCouponListNegative(@PageableDefault Pageable pageable, Model model) {
        Long memberNo = memberUtils.getMemberNo();
        memberUtils.modelRequestMemberNo(model);

        PageResponse<GetCouponResponseDto> negativeCoupons = couponService.getNegativeCoupons(
                pageable, memberNo);

        model.addAttribute("negativeList", negativeCoupons.getContent());
        model.addAttribute("negativeTotalPages", negativeCoupons.getTotalPages());
        model.addAttribute("negativeCurrentPage", negativeCoupons.getNumber());
        model.addAttribute("negativeIsNext", negativeCoupons.isNext());
        model.addAttribute("negativeIsPrevious", negativeCoupons.isPrevious());
        model.addAttribute("negativePageButtonNum", 5);

        return "mypage/mycouponNegative";
    }


    /**
     * 로그인 화면을 보여주는 메소드.
     *
     * @return 로그인 화면.
     */
    @GetMapping("/login")
    public String loginPageForm() {
        return "member/login";
    }

    /**
     * 로그아웃.
     *
     * @param response the response
     * @return 로그아웃 후 메인화면으로 리다이렉트.
     */
    @GetMapping("/logout")
    public String logoutSubmit(HttpServletResponse response) {
        memberService.logout(response);

        return "redirect:/";
    }

    /**
     * 회원의 이름을 바꾸는 메서드입니다.
     * 마이페이지 내정보로 리다이렉트 됩니다.
     *
     * @param name 변경할 이름
     * @return 마이페이지 내정보.
     */
    @PostMapping("/members/name")
    @Auth
    public String memberExchangeName(@RequestParam("exchangeName") String name) {
        Long memberNo = memberUtils.getMemberNo();
        memberService.modifyMemberName(memberNo, name);

        return REDIRECT_MY_PAGE;
    }

    /**
     * 회원의 닉네임을 변경하기위한 메서드입니다.
     * 마이페이지의 내정보창으로 반환됩니다.
     *
     * @param nickname the nickname
     * @return the string
     */
    @PostMapping("/members/nickname")
    @Auth
    public String memberExchangeNickname(@RequestParam("exchangeNickname") String nickname) {
        Long memberNo = memberUtils.getMemberNo();
        memberService.modifyMemberNickName(memberNo, nickname);

        return REDIRECT_MY_PAGE;
    }

    /**
     * 회원의 이메일을 변경할때쓰이는 메서드입니다.
     * 마이페이지 내정보창으로 이동합니다.
     *
     * @param email 변경할 이메일.
     * @return 마이페이지 내정보.
     */
    @PostMapping("/members/email")
    @Auth
    public String memberExchangeEmail(@RequestParam("exchangeEmail") String email) {
        Long memberNo = memberUtils.getMemberNo();
        memberService.modifyMemberEmail(memberNo, email);

        return REDIRECT_MY_PAGE;
    }

    /**
     * 회원의 휴대전화번호가 변경될때 사용되는 메서드입니다.
     * 마이페이의 내정보 페이지로 이동합니다.
     *
     * @param phone 휴대전화번호기입
     * @return 마이페이지 내정보
     */
    @PostMapping("/members/phone")
    @Auth
    public String memberExchangePhone(@RequestParam("exchangePhone") String phone) {
        Long memberNo = memberUtils.getMemberNo();
        memberService.modifyMemberPhone(memberNo, phone);

        return REDIRECT_MY_PAGE;
    }

    /**
     * 멤버의 비밀번호가 변경될때 쓰이는 메서드입니다.
     * 마이페이지의 내정보 페이지로 이동합니다.
     *
     * @param password 변경할 비밀번호
     * @return the string
     */
    @PostMapping("/members/password")
    @Auth
    public String memberExchangePassword(@RequestParam("exchangePwd") String password) {
        Long memberNo = memberUtils.getMemberNo();
        memberService.modifyMemberPassword(memberNo, password);

        return REDIRECT_MY_PAGE;
    }


    /**
     * 멤버의 기준주소지를 변경하기위한 메서드입니다.
     *
     * @param addressNo 주소번호
     * @return the string
     */
    @PostMapping("/members/addresses/{addressNo}")
    @Auth
    public String memberExchangeBaseAddress(@PathVariable("addressNo") Long addressNo) {
        Long memberNo = memberUtils.getMemberNo();
        memberService.modifyMemberAddress(memberNo, addressNo);

        return REDIRECT_MY_PAGE;
    }

    /**
     * 회원을 탈퇴하기위한 메서드입니다.
     *
     * @return 메인페이지
     */
    @Auth
    @PostMapping("/members")
    public String memberDelete() {
        Long memberNo = memberUtils.getMemberNo();
        memberService.memberDelete(memberNo);

        return "redirect:/logout";
    }

    /**
     * 회원의 주소를 추가하기위한 메서드입니다.
     *
     * @param requestDto 주소를 등록하기위한 정보.
     * @return the string
     */
    @PostMapping("/members/addresses")
    @Auth
    public String memberAddAddress(MemberAddressRequestDto requestDto) {
        Long memberNo = memberUtils.getMemberNo();
        memberService.addMemberAddress(memberNo, requestDto);

        return REDIRECT_MY_PAGE;
    }

    /**
     * 회원의 주소를 삭제하기위한 메서드입니다.
     *
     * @param addressNo 삭제할 주소번호 기압.
     * @return the string
     */
    @PostMapping("/members/addresses-delete/{addressNo}")
    @Auth
    public String memberDeleteAddress(@PathVariable("addressNo") Long addressNo) {
        Long memberNo = memberUtils.getMemberNo();
        memberService.deleteMemberAddress(memberNo, addressNo);

        return REDIRECT_MY_PAGE;
    }
}
