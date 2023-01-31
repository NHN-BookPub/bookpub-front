package com.nhnacademy.bookpub.bookpubfront.order.controller;

import com.nhnacademy.bookpub.bookpubfront.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 주문 관리자 페이지 뷰를 위한 컨트롤러.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Controller
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {
    private final OrderService orderService;

    /**
     * 관리자용 주문 뷰.
     *
     * @param model 모델.
     * @param pageable 페이징.
     * @return 주문 리스트 뷰.
     */
    @GetMapping()
    public String adminOrderView(Model model, @PageableDefault Pageable pageable) {
        model.addAttribute("orderList", orderService.getOrderList(pageable));

        return "/admin/order/orderMain";
    }
}
