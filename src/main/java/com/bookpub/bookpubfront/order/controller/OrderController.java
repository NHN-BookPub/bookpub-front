package com.bookpub.bookpubfront.order.controller;

import com.bookpub.bookpubfront.order.dto.response.GetAddressResponseDto;
import com.bookpub.bookpubfront.order.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/order/test")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public String test(Model model) {
        List<GetAddressResponseDto> memberAddresses = orderService.getMemberAddresses();
        model.addAttribute("address", memberAddresses);
        return "order/main";
    }

}
