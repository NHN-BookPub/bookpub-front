package com.nhnacademy.bookpub.bookpubfront.product.controller;

import static com.nhnacademy.bookpub.bookpubfront.state.DeliveryFeeType.DELIVERY_FEE;
import static com.nhnacademy.bookpub.bookpubfront.state.DeliveryFeeType.DELIVERY_FREE_FEE_STANDARD;

import com.nhnacademy.bookpub.bookpubfront.cart.util.CartUtils;
import com.nhnacademy.bookpub.bookpubfront.category.dto.response.GetCategoryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.category.service.CategoryService;
import com.nhnacademy.bookpub.bookpubfront.category.util.CategoryUtils;
import com.nhnacademy.bookpub.bookpubfront.inquiry.dto.response.GetInquirySummaryProductResponseDto;
import com.nhnacademy.bookpub.bookpubfront.inquiry.service.InquiryService;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductByCategoryResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.dto.response.GetProductDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.response.GetProductTypeStateCodeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.service.ProductTypeStateCodeService;
import com.nhnacademy.bookpub.bookpubfront.product.service.ProductService;
import com.nhnacademy.bookpub.bookpubfront.review.service.ReviewService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import com.nhnacademy.bookpub.bookpubfront.utils.Utils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 관리자를 제외한 상품 컨트롤러.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
@RequestMapping
public class ProductController {
    private final ProductService productService;
    private final ReviewService reviewService;
    private final CategoryService categoryService;
    private final InquiryService inquiryService;
    private final CartUtils cartUtils;
    private final CategoryUtils categoryUtils;
    private final MemberUtils memberUtils;
    private final ProductTypeStateCodeService productTypeStateCodeService;
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String CART = "CART";
    private static final String RECENT_VIEW_COOKIE = "RECENT-VIEW";
    private static final String DIVIDE_LINE = "======";

    /**
     * 상품 단건 상세 조회를 위한 메서드.
     *
     * @param productNo   상품 번호
     * @param cookie      쿠키
     * @param inquiryPage 상품문의 페이징
     * @param model       view 요청을 보낼 request
     * @return 상품 상세 조회 페이지
     */
    @GetMapping("/products/{productNo}")
    public String viewProduct(@PathVariable("productNo") Long productNo,
                              @CookieValue(name = CART, required = false) Cookie cookie,
                              @CookieValue(name = RECENT_VIEW_COOKIE, required = false) Cookie recentViewCookie,
                              @RequestParam(value = "inquiryPage", defaultValue = "0") int inquiryPage,
                              Model model) {
        GetProductDetailResponseDto product = productService.findProduct(productNo);

        addRecentView(productNo, recentViewCookie, product);

        PageResponse<GetInquirySummaryProductResponseDto> inquiries =
                inquiryService.getProductInquiryList(PageRequest.of(inquiryPage, 10), productNo);

        cartUtils.getCountInCart(cookie.getValue(), model);
        categoryUtils.categoriesView(model);
        memberUtils.modelRequestMemberNo(model);

        model.addAttribute("product", product);
        model.addAttribute("free", DELIVERY_FREE_FEE_STANDARD.getFee());
        model.addAttribute("deliveryFee", DELIVERY_FEE.getFee());
        model.addAttribute("reviewInfo", reviewService.getProductReviewInfo(productNo));
        model.addAttribute("inquiryList", inquiries.getContent());
        model.addAttribute("inquiryTotalPages", inquiries.getTotalPages());
        model.addAttribute("inquiryCurrentPage", inquiries.getNumber());
        model.addAttribute("inquiryIsNext", inquiries.isNext());
        model.addAttribute("inquiryIsPrevious", inquiries.isPrevious());
        model.addAttribute("inquiryPageButtonNum", 5);

        return "product/productDetail";
    }

    /**
     * 상품 상세 조회 시 redis 상품정보를 저장하는 메서드.
     *
     * @param productNo        상품 번호
     * @param recentViewCookie 쿠키
     * @param product          상품
     */
    private void addRecentView(Long productNo, Cookie recentViewCookie, GetProductDetailResponseDto product) {
        if (Objects.nonNull(recentViewCookie)) {
            redisTemplate.opsForZSet().add(recentViewCookie.getValue(),
                    productNo + DIVIDE_LINE + product.getTitle() + DIVIDE_LINE + product.getThumbnail(),
                    Double.parseDouble(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))));
        }
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
        cartUtils.getCountInCart(cookie.getValue(), model);
        categoryUtils.categoriesView(model);
        memberUtils.modelRequestMemberNo(model);

        Utils.settingPagination(model,
                productService.findProductByCategory(categoryNo, pageable),
                "products");

        GetCategoryResponseDto category = categoryService.getCategory(categoryNo);
        model.addAttribute("categoryNo", categoryNo);
        model.addAttribute("categoryName", category.getCategoryName());

        return "product/productListByCategory";
    }

    /**
     * 상품 유형으로 상품을 조회합니다.
     *
     * @param typeNo   유형번호
     * @param pageable 페이징
     * @param cookie   쿠키
     * @param model    모델
     * @return 상품들
     */
    @GetMapping("/types/{typeNo}")
    public String viewProductsByType(@PathVariable Integer typeNo,
                                     @PageableDefault Pageable pageable,
                                     @CookieValue(name = CART, required = false) Cookie cookie,
                                     Model model) {
        PageResponse<GetProductByCategoryResponseDto> products = productService.findProductByType(typeNo, pageable);
        GetProductTypeStateCodeResponseDto type = productTypeStateCodeService.getProductTypeStateCodes().get(typeNo - 1);

        cartUtils.getCountInCart(cookie.getValue(), model);
        categoryUtils.categoriesView(model);
        memberUtils.modelRequestMemberNo(model);

        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", products.getNumber());
        model.addAttribute("isNext", products.isNext());
        model.addAttribute("isPrevious", products.isPrevious());
        model.addAttribute("pageButtonNum", 5);
        model.addAttribute("typeNo", typeNo);
        model.addAttribute("typeName", type.getCodeName());

        return "product/productListByType";
    }

    /**
     * 이북 리스트를 보여줍니다.
     *
     * @param pageable 페이징
     * @param cookie   쿠키
     * @param model    모델
     * @return 이북 리스트 뷰
     */
    @GetMapping("/products/ebooks")
    public String viewEbooks(@PageableDefault Pageable pageable,
                             @CookieValue(name = CART, required = false) Cookie cookie,
                             Model model) {
        PageResponse<GetProductByCategoryResponseDto> products = productService.getEbooks(pageable);

        cartUtils.getCountInCart(cookie.getValue(), model);
        categoryUtils.categoriesView(model);

        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", products.getNumber());
        model.addAttribute("isNext", products.isNext());
        model.addAttribute("isPrevious", products.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "product/productEbookList";
    }
}
