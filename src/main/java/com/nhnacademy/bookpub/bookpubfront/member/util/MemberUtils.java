package com.nhnacademy.bookpub.bookpubfront.member.util;

import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 * 로그인한 memberNo model에 담기 위한 유틸.
 * 로그인한 사람은 멤버 번호가 저장.
 * 로그인하지 않은 사람은 -1이 저장.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
public class MemberUtils {

    private final MemberService memberService;

    /**
     * 현재 로그인한 사용자의 멤버 번호를 model 쪽에 담는 메서드.
     * 로그인 한 사람     : 멤버 번호.
     * 로그인 안한 사람    : -1.
     *
     * @param model model
     */
    public void getMemberNo(Model model) {
        String principal =
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long memberNo = -1L;

        if (!principal.equals("anonymousUser")) {
            MemberDetailResponseDto member
                    = memberService.getApiMember(Long.parseLong(principal));
            memberNo = member.getMemberNo();
        }

        model.addAttribute("memberNo", memberNo);
    }
}
