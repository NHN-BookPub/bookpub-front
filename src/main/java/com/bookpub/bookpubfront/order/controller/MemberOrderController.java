package com.bookpub.bookpubfront.order.controller;

import com.bookpub.bookpubfront.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/list")
    public String orderListView(Model model, Pageable pageable) {
        model.addAttribute("orderList", orderService.getOrderListByMemberNo(397L, pageable));
        model.addAttribute("nowPage", pageable.getPageNumber());

        return "mypage/orderList";
    }

    @GetMapping
    public String orderDetailView(Model model, @RequestParam Long orderNo) {
        model.addAttribute("orderDetail", orderService.getOrderDetailByNo(orderNo));

        return "mypage/orderDetail";
    }
}
