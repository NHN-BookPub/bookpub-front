package com.nhnacademy.bookpub.bookpubfront.product.controller;

import static com.nhnacademy.bookpub.bookpubfront.state.DeliveryFeeType.DELIVERY_FEE;
import static com.nhnacademy.bookpub.bookpubfront.state.DeliveryFeeType.DELIVERY_FREE_FEE_STANDARD;

import com.nhnacademy.bookpub.bookpubfront.cart.util.CartUtils;
import com.nhnacademy.bookpub.bookpubfront.category.dto.response.GetCategoryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.category.service.CategoryService;
import com.nhnacademy.bookpub.bookpubfront.category.util.CategoryUtils;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductByCategoryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.service.ProductService;
import com.nhnacademy.bookpub.bookpubfront.review.service.ReviewService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping
public class ProductController {

    private final ProductService productService;
    private final ReviewService reviewService;
    private final CategoryService categoryService;
    private final CartUtils cartUtils;
    private final CategoryUtils categoryUtils;
    private final MemberUtils memberUtils;
    private static final String CART = "CART";

    /**
     * 상품 단건 상세 조회를 위한 메서드.
     *
     * @param productNo 상품 번호
     * @param cookie    쿠키
     * @param model     view 요청을 보낼 request
     * @return 상품 상세 조회 페이지
     */
    @GetMapping("/products/{productNo}")
    public String viewProduct(@PathVariable("productNo") Long productNo,
                              @CookieValue(name = CART, required = false) Cookie cookie,
                              Model model) {
        GetProductDetailResponseDto product = productService.findProduct(productNo);

        cartUtils.getCountInCart(cookie.getValue(), model);
        categoryUtils.categoriesView(model);
        memberUtils.getMemberNo(model);

        model.addAttribute("product", product);
        model.addAttribute("free", DELIVERY_FREE_FEE_STANDARD.getFee());
        model.addAttribute("deliveryFee", DELIVERY_FEE.getFee());
        model.addAttribute("reviewInfo", reviewService.getProductReviewInfo(productNo));

        return "product/productDetail";
    }

    /**
     * 카테고리를 기준으로 상품들을 조회.
     *
     * @param categoryNo 카테고리 번호
     * @param pageable   페이징 정보
     * @param cookie     쿠키
     * @param model      view 요청을 보낼 request
     * @return 카테고리별 상품 조회 뷰
     */
    @GetMapping("/categories/{categoryNo}")
    public String viewProductsByCategory(@PathVariable("categoryNo") Integer categoryNo,
                                         @PageableDefault Pageable pageable,
                                         @CookieValue(name = CART, required = false) Cookie cookie,
                                         Model model) {
        PageResponse<GetProductByCategoryResponseDto> products = productService.findProductByCategory(categoryNo, pageable);
        GetCategoryResponseDto category = categoryService.getCategory(categoryNo);

        cartUtils.getCountInCart(cookie.getValue(), model);
        categoryUtils.categoriesView(model);
        memberUtils.getMemberNo(model);

        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", products.getNumber());
        model.addAttribute("isNext", products.isNext());
        model.addAttribute("isPrevious", products.isPrevious());
        model.addAttribute("pageButtonNum", 5);
        model.addAttribute("categoryNo", categoryNo);
        model.addAttribute("categoryName", category.getCategoryName());

        return "product/productListByCategory";
    }

}
