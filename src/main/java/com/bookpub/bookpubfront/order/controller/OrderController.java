package com.bookpub.bookpubfront.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Some description here.
 *
 * @author : 여운석, 임태원
 * @since : 1.0
 **/
@Controller
@RequestMapping("/order/test")
public class OrderController {
    @GetMapping
    public String test() {
        return"order/main";
    }

}
