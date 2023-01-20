package com.bookpub.bookpubfront.product.controller;


import com.bookpub.bookpubfront.category.dto.response.GetParentCategoryWithChildrenResponseDto;
import com.bookpub.bookpubfront.category.service.CategoryService;
import com.bookpub.bookpubfront.product.dto.reqeust.InputProductFormRequestDto;
import com.bookpub.bookpubfront.product.dto.response.GetProductDetailResponseDto;
import com.bookpub.bookpubfront.product.dto.response.GetProductListResponseDto;
import com.bookpub.bookpubfront.product.relationship.dto.response.GetProductPolicyResponseDto;
import com.bookpub.bookpubfront.product.relationship.dto.response.GetProductSaleStateCodeResponseDto;
import com.bookpub.bookpubfront.product.relationship.dto.response.GetProductTypeStateCodeResponseDto;
import com.bookpub.bookpubfront.product.relationship.service.ProductPolicyService;
import com.bookpub.bookpubfront.product.relationship.service.ProductSaleStateCodeService;
import com.bookpub.bookpubfront.product.relationship.service.ProductTypeStateCodeService;
import com.bookpub.bookpubfront.product.service.ProductService;
import com.bookpub.bookpubfront.tag.dto.GetTagResponseDto;
import com.bookpub.bookpubfront.tag.service.TagService;
import com.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 관리자페이지에서 상품과 관련된 컨트롤러.
 *
 * @author : 박경서, 정유진, 김서현
 * @since : 1.0
 **/
@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductTypeStateCodeService productTypeStateCodeService;
    private final ProductSaleStateCodeService productSaleStateCodeService;
    private final ProductPolicyService productPolicyService;
    private final TagService tagService;
    private static final String REDIRECT = "redirect:/admin/products";

    /**
     * 관리자 페이지에서 상품 목록을 보기 위한 맵핑.
     *
     * @param pageable 페이지 정보
     * @param model    view 로 보낼 request
     * @return 상품 목록 화면
     */
    @GetMapping
    public String products(@PageableDefault Pageable pageable, Model model) {
        List<GetTagResponseDto> tags = tagService.findAllTags();
        List<GetParentCategoryWithChildrenResponseDto> categories =
                categoryService.getParentCategoryWithChildren();
        List<GetProductTypeStateCodeResponseDto> typeStateCodes =
                productTypeStateCodeService.getProductTypeStateCodes();
        List<GetProductSaleStateCodeResponseDto> saleStateCodes =
                productSaleStateCodeService.getProductSaleStateCodes();
        List<GetProductPolicyResponseDto> policies = productPolicyService.getProductPolicies();
        PageResponse<GetProductListResponseDto> products = productService.findAllProducts(pageable);

        model.addAttribute("tags", tags);
        model.addAttribute("categories", categories);
        model.addAttribute("typeStateCodes", typeStateCodes);
        model.addAttribute("saleStateCodes", saleStateCodes);
        model.addAttribute("policies", policies);
        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", products.getNumber());
        model.addAttribute("pageButtonNum", 100);

        return "admin/product/productIndex";
    }

    /**
     * 상품 상세 정보 화면을 위한 맵핑.
     *
     * @param productNo 상품 번호
     * @param model     view 로 보낼 request
     * @return 상품 상세 정보 화
     */
    @PostMapping("/{productNo}")
    public String productDetail(@PathVariable("productNo") Long productNo, Model model) {
        GetProductDetailResponseDto product = productService.findProduct(productNo);
        model.addAttribute("product", product);

        return "admin/product/productDetail";
    }

    /**
     * 상품 등록을 처리하는 매핑.
     *
     * @param inputProduct 상품 등록을 위한 DTO
     * @param tagList      선택된 태그를 담는 리스트
     * @return 상품 목록 화면으로 Redirect
     */
    @PostMapping("/add")
    public String addProduct(@ModelAttribute InputProductFormRequestDto inputProduct,
                             @RequestParam(value = "tagList", required = false) List<Integer> tagList) {
        productService.createCategory(inputProduct, tagList);

        return REDIRECT;
    }

    /**
     * 상품 삭제 여부를 처리할 매핑.
     *
     * @param productNo 상품 번호
     * @return 상품 목록 화면으로 Redirect
     */
    @PostMapping("/deleted/{productNo}")
    public String setDeletedProduct(@PathVariable("productNo") Long productNo) {
        productService.setProductDeleted(productNo);

        return REDIRECT;
    }

}
