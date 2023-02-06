package com.nhnacademy.bookpub.bookpubfront.product.relationship.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.request.CreateProductPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.request.ModifyProductPolicyRequestDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.response.GetProductPolicyResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.service.ProductPolicyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 관리자페이지에서 상품 정책을 다루기 위한  컨트롤러.
 *
 * @author : 박경서, 정유진
 * @since : 1.0
 */
@Controller
@RequestMapping("/admin/product/product-policies")
@RequiredArgsConstructor
@Slf4j
public class AdminProductPolicyController {
    private final ProductPolicyService productPolicyService;

    /**
     * 관리자 페이지에서 상품 정책을 조회할 수 있도록 하는 메소드.
     *
     * @param model view 로 정보를 보내는 request
     * @return 상품정책 페이지
     */
    @GetMapping
    public String productPolicyList(Model model) {
        List<GetProductPolicyResponseDto> productPolicies =
                productPolicyService.getProductPolicies();

        model.addAttribute("productPolicies", productPolicies);

        return "admin/product/productPolicyPage";
    }

    /**
     * 관리자 페이지에서 상품 정책을 등록할 수 있도록 하는 메소드.
     *
     * @param createRequestDto 등록할 상품 정책 정보를 담은 dto
     * @return 상품정책 페이지로 리다이렉트
     */
    @Auth
    @PostMapping
    public String productPolicyAdd(@ModelAttribute CreateProductPolicyRequestDto createRequestDto) {
        productPolicyService.createProductPolicy(createRequestDto);

        return "redirect:/admin/product/product-policies";
    }

    /**
     * 관리자 페이지에서 상품 정책을 수정할 수 있도록 하는 메소드.
     *
     * @param policyNo         수정할 상품정책 번호
     * @param modifyRequestDto 수정할 성퓸 정책 정보를 담은 dto
     * @return 상품정책 페이지로 리다이렉트
     */
    @PostMapping("/modify")
    @Auth
    public String productPolicyModify(@RequestParam("policyNo") Integer policyNo,
                                      @ModelAttribute
                                      ModifyProductPolicyRequestDto modifyRequestDto) {
        productPolicyService.modifyProductPolicy(policyNo, modifyRequestDto);

        return "redirect:/admin/product/product-policies";
    }

}
