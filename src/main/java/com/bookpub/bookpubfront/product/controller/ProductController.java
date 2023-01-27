package com.bookpub.bookpubfront.product.controller;

import static com.bookpub.bookpubfront.state.DeliveryFeeType.DELIVERY_FEE;
import static com.bookpub.bookpubfront.state.DeliveryFeeType.DELIVERY_FREE_FEE_STANDARD;

import com.bookpub.bookpubfront.product.dto.response.GetProductDetailResponseDto;
import com.bookpub.bookpubfront.product.service.ProductService;
import com.bookpub.bookpubfront.utils.CartUtils;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    /**
     * 상품 단건 상세 조회를 위한 메서드.
     *
     * @param productNo 상품 번호
     * @param request   HTTP request
     * @param model     view 요청을 보낼 request
     * @return 상품 상세 조회 페이지
     */
    @GetMapping("/view/{productNo}")
    public String viewProduct(@PathVariable("productNo") Long productNo,
                              HttpServletRequest request,
                              Model model) {
        GetProductDetailResponseDto product = productService.findProduct(productNo);
        CartUtils.getCountInCart(request, model);

        model.addAttribute("product", product);
        model.addAttribute("free", DELIVERY_FREE_FEE_STANDARD.getFee());
        model.addAttribute("deliveryFee", DELIVERY_FEE.getFee());

        return "product/productDetail";
    }

}
