package com.bookpub.bookpubfront.member.controller;

import com.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.bookpub.bookpubfront.member.dto.response.MemberResponseDto;
import com.bookpub.bookpubfront.member.service.MemberService;
import com.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 멤버를 다루는 멤버 컨트롤러입니다.
 *
 * @author : 유호철
 * @since : 1.0
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {
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

}
