package com.nhnacademy.bookpub.bookpubfront.purchase.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.dto.response.GetProductTypeStateCodeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.product.relationship.service.ProductTypeStateCodeService;
import com.nhnacademy.bookpub.bookpubfront.purchase.dto.request.CreatePurchaseRequestDto;
import com.nhnacademy.bookpub.bookpubfront.purchase.dto.response.GetPurchaseListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.purchase.service.PurchaseService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 구매이력 컨트롤러 입니다.
 * 모든 메소드는 관리자만 이용할 수 있습니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final ProductTypeStateCodeService productTypeStateCodeService;
    private static final String REDIRECT = "redirect:/admin/purchases";

    /**
     * 최근 등록순으로 모든 매입이력을 조회합니다.
     *
     * @param model 모델
     * @param pageable 페이징
     * @return 모든 매입이력(최신순)
     */
    @Auth
    @GetMapping("/admin/purchases")
    public String getPurchases(Model model, @PageableDefault Pageable pageable) {
        PageResponse<GetPurchaseListResponseDto> purchases =
                purchaseService.getPurchases(pageable);
        List<GetProductTypeStateCodeResponseDto> typeStateCodes =
                productTypeStateCodeService.getProductTypeStateCodes();

        model.addAttribute("typeStateCodes", typeStateCodes);
        model.addAttribute("purchases", purchases.getContent());
        model.addAttribute("totalPages", purchases.getTotalPages());
        model.addAttribute("currentPage", purchases.getNumber());
        model.addAttribute("isNext", purchases.isNext());
        model.addAttribute("isPrevious", purchases.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "admin/product/purchaseIndex";
    }

    /**
     * 매입이력을 등록합니다.
     * 상품의 재고도 함께 증가합니다.
     *
     * @param requestDto 등록시 필요한 dto
     * @return 매입이력 조회로 다시 이동
     */
    @Auth
    @PostMapping("/admin/purchases")
    public String createPurchase(CreatePurchaseRequestDto requestDto) {
        purchaseService.createPurchaseAndAlarm(requestDto);

        return REDIRECT;
    }

    /**
     * 상품번호로 이력을 조회합니다.
     *
     * @param model 모델
     * @param pageable 페이징
     * @param productNo 상품번호
     * @return 상품 단건에 대한 매입이력
     */
    @Auth
    @GetMapping("/admin/purchases/{productNo}")
    public String getPurchasesByProductNo(Model model,
                                          @PageableDefault Pageable pageable,
                                          @PathVariable Long productNo) {
        PageResponse<GetPurchaseListResponseDto> purchases =
                purchaseService.getPurchasesByProductNo(productNo, pageable);
        List<GetProductTypeStateCodeResponseDto> typeStateCodes =
                productTypeStateCodeService.getProductTypeStateCodes();

        model.addAttribute("typeStateCodes", typeStateCodes);
        model.addAttribute("purchases", purchases.getContent());
        model.addAttribute("totalPages", purchases.getTotalPages());
        model.addAttribute("currentPage", purchases.getNumber());
        model.addAttribute("isNext", purchases.isNext());
        model.addAttribute("isPrevious", purchases.isPrevious());
        model.addAttribute("pageButtonNum", 5);

        return "admin/product/purchaseIndex";
    }
}
