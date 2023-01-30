package com.nhnacademy.bookpub.bookpubfront.order.controller;

import com.nhnacademy.bookpub.bookpubfront.member.dto.response.MemberDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.service.MemberService;
import com.nhnacademy.bookpub.bookpubfront.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 회원의 주문에 관련된 뷰를 위한 컨트롤러입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class MemberOrderController {
    private final OrderService orderService;
    private final MemberService memberService;

    /**
     * 멤버의 주문 리스트 조회를 위한 메소드입니다.
     *
     * @param model 모델.
     * @param pageable 페이징.
     * @return 주문리스트 뷰를 반환합니다.
     */
    @GetMapping("/list")
    public String orderListView(Model model, Pageable pageable) {
        model.addAttribute("orderList", orderService.getOrderListByMemberNo(397L, pageable));

        return "mypage/orderList";
    }

    /**
     * 주문 상세 뷰를 위한 메소드입니다.
     *
     * @param model 모델.
     * @param orderNo 주문번호.
     * @return 주문상세 뷰를 반환합니다.
     */
    @GetMapping("/detail")
    public String orderDetailView(Model model, @RequestParam Long orderNo) {
        model.addAttribute("orderDetail", orderService.getOrderDetailByNo(orderNo));

        return "mypage/orderDetail";
    }

    /**
     * 회원, 비회원의 주문 view로 넘겨주는 컨트롤러.
     *
     * @param model view에 데이터 전달해주는 클래스.
     * @return order view.
     */
    @GetMapping
    public String memberOrder(Model model) {
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
