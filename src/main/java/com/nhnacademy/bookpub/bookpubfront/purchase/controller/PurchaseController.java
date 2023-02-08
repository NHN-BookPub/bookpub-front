package com.nhnacademy.bookpub.bookpubfront.purchase.controller;


import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.purchase.dto.request.CreatePurchaseRequestDto;
import com.nhnacademy.bookpub.bookpubfront.purchase.dto.response.GetPurchaseListResponseDto;
import com.nhnacademy.bookpub.bookpubfront.purchase.service.PurchaseService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
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
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;
    private static final String REDIRECT = "redirect:/admin/purchases";

    @Auth
    @GetMapping("/admin/purchases")
    public String getPurchases(Model model, @PageableDefault Pageable pageable) {
        PageResponse<GetPurchaseListResponseDto> purchases =
                purchaseService.getPurchases(pageable);

        model.addAttribute("purchases", purchases.getContent());
        model.addAttribute("totalPages", purchases.getTotalPages());
        model.addAttribute("currentPage", purchases.getNumber());
        model.addAttribute("pageButtonNum", 100);

        return "/admin/product/purchaseIndex";
    }

    @Auth
    @PostMapping("/admin/purchases")
    public String createPurchase(CreatePurchaseRequestDto requestDto) {
        purchaseService.createPurchase(requestDto);

        return REDIRECT;
    }

    @Auth
    @GetMapping("/admin/purchases/{productNo}")
    public String getPurchasesByProductNo(Model model,
                                          @PageableDefault Pageable pageable,
                                          @PathVariable Long productNo) {
        PageResponse<GetPurchaseListResponseDto> purchases =
                purchaseService.getPurchasesByProductNo(productNo, pageable);

        model.addAttribute("purchases", purchases.getContent());
        model.addAttribute("totalPages", purchases.getTotalPages());
        model.addAttribute("currentPage", purchases.getNumber());
        model.addAttribute("pageButtonNum", 100);

        return "/admin/product/purchaseIndex";
    }
}
