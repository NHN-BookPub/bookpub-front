package com.bookpub.bookpubfront.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 장바구니를 위한 컨트롤러.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping
    public String cart() {
        return "cart/shoppingCart";
    }
}
