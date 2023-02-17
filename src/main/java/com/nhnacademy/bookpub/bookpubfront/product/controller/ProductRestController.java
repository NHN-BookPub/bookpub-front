package com.nhnacademy.bookpub.bookpubfront.product.controller;

import com.nhnacademy.bookpub.bookpubfront.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 상품 restController.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@RestController
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    @GetMapping("/products/purchase/{productNo}/{memberNo}")
    public boolean isPurchaseUser(@PathVariable String memberNo,
                                  @PathVariable String productNo) {
        return productService.isPurchaseProduct(productNo, memberNo);
    }
}
