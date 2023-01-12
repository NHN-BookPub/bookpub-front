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
 **/

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

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

    @GetMapping("/admin/members/{memberNo}")
    public String adminMemberInfo(@PathVariable("memberNo") Long memberNo,
                                  Model model) {
        MemberDetailResponseDto member = memberService.getMember(memberNo);

        model.addAttribute("member", member);

        return "admin/member/memberInfo";
    }

    @PostMapping("/admin/members/{memberNo}")
    public String adminMemberBlock(@PathVariable("memberNo") Long memberNo,
                                   Pageable pageable) {
        memberService.memberBlock(memberNo);
        return "redirect:/admin/members?page=" + pageable.getPageNumber() + "size=" + pageable.getPageSize();
    }

    @GetMapping("/members/{memberNo}")
    public String memberInfo(@PathVariable("memberNo") Long memberNo,
                             Model model) {
        MemberDetailResponseDto member = memberService.getMember(memberNo);
        model.addAttribute("member", member);

        return "members/memberInfo";
    }

}
