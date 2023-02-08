package com.nhnacademy.bookpub.bookpubfront.payment.controller;

import com.nhnacademy.bookpub.bookpubfront.config.TossConfig;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.service.OrderService;
import com.nhnacademy.bookpub.bookpubfront.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 결제 컨트롤러.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;
    private final OrderService orderService;
    private final TossConfig tossConfig;

    /**
     * 결제페이지를 로딩하는 컨트롤러입니다.
     *
     * @param orderNo 주문번호.
     * @param model   model.
     * @return 결제페이지.
     */
    @GetMapping("/{orderNo}")
    public String paymentPage(@PathVariable Long orderNo, Model model) {
        GetOrderDetailResponseDto order = orderService.getOrderDetailByNo(orderNo);
        model.addAttribute("order", order);
        model.addAttribute("toss", tossConfig.makeTossProvider());
        return "payment/main";
    }

    /**
     * 결제 생성 성공 시 리다이렉트 되서 들어오는 컨트롤러 url.
     *
     * @param orderId    주문번호.
     * @param paymentKey 결제 생성 키.
     * @param amount     금액.
     * @return successPage.
     */
    @GetMapping("/success")
    public String successPage(@RequestParam String orderId,
                              @RequestParam String paymentKey,
                              @RequestParam Long amount) {
        paymentService.verifyPayment(orderId, amount);
        paymentService.createPayment(orderId, paymentKey, amount);

        return "redirect:/";
    }

}
