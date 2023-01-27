package com.bookpub.bookpubfront.order.controller;

import com.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.bookpub.bookpubfront.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 주문의 컨트롤러입니다.
 *
 * @author : 여운석, 임태원
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
@Slf4j
public class OrderController {
    private final MemberService memberService;

    /**
     * 회원, 비회원의 주문 view로 넘겨주는 컨트롤러.
     *
     * @param model view에 데이터 전달해주는 클래스.
     * @return order view.
     */
    @GetMapping
    public String test(Model model) {
        String principal =
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!principal.equals("anonymousUser")) {
            MemberDetailResponseDto member
                    = memberService.getMember(Long.parseLong(principal));
            model.addAttribute("member", member);
        }
        return "order/main";
    }

}
