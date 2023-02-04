package com.nhnacademy.bookpub.bookpubfront.product.controller;

import static com.nhnacademy.bookpub.bookpubfront.state.DeliveryFeeType.DELIVERY_FEE;
import static com.nhnacademy.bookpub.bookpubfront.state.DeliveryFeeType.DELIVERY_FREE_FEE_STANDARD;

import com.nhnacademy.bookpub.bookpubfront.cart.util.CartUtils;
import com.nhnacademy.bookpub.bookpubfront.category.util.CategoryUtils;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.service.ProductService;
import com.nhnacademy.bookpub.bookpubfront.review.service.ReviewService;
import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 관리자를 제외한 상품 컨트롤러.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ReviewService reviewService;
    private final CartUtils cartUtils;
    private final CategoryUtils categoryUtils;
    private static final String CART = "CART";

    /**
     * 상품 단건 상세 조회를 위한 메서드.
     *
     * @param productNo 상품 번호
     * @param cookie    쿠키
     * @param model     view 요청을 보낼 request
     * @return 상품 상세 조회 페이지
     */
    @GetMapping("/view/{productNo}")
    public String viewProduct(@PathVariable("productNo") Long productNo,
                              @CookieValue(name = CART, required = false) Cookie cookie,
                              Model model) {
        GetProductDetailResponseDto product = productService.findProduct(productNo);

        cartUtils.getCountInCart(cookie.getValue(), model);
        categoryUtils.categoriesView(model);
        model.addAttribute("product", product);
        model.addAttribute("free", DELIVERY_FREE_FEE_STANDARD.getFee());
        model.addAttribute("deliveryFee", DELIVERY_FEE.getFee());
        model.addAttribute("reviewInfo", reviewService.getProductReviewInfo(productNo));

        return "product/productDetail";
    }

}
