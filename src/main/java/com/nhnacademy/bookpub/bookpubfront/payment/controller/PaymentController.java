package com.nhnacademy.bookpub.bookpubfront.payment.controller;

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
public class PaymentController {

    @GetMapping("/{orderNo}")
    public String paymentPage(@PathVariable String orderNo, Model model) {
        model.addAttribute("orderNo", orderNo);
        return "payment/main";
    }

}
