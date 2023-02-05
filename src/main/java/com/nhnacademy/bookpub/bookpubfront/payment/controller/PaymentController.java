package com.nhnacademy.bookpub.bookpubfront.payment.controller;

import com.nhnacademy.bookpub.bookpubfront.config.TossConfig;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 결제 컨트롤러.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final OrderService orderService;
    private final TossConfig tossConfig;

    @GetMapping("/{orderNo}")
    public String paymentPage(@PathVariable Long orderNo, Model model) {
        GetOrderDetailResponseDto order = orderService.getOrderDetailByNo(orderNo);
        model.addAttribute("order", order);
        model.addAttribute("toss", tossConfig);
        return "payment/main";
    }

}
