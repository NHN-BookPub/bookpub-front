package com.nhnacademy.bookpub.bookpubfront.subscribe.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request.CreateSubscribeRequestDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request.ModifySubscribeRequestDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.response.GetSubscribeDetailResponseDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.response.GetSubscribeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.service.SubscribeService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 구독관련 컨트롤러 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Slf4j
@Controller
@RequiredArgsConstructor
public class SubscribeController {
    private final SubscribeService service;

    private static final String RE_SUBSCRIBE = "redirect:/admin/subscribes";

    @GetMapping("/admin/subscribes")
    public String subscribeMain(@PageableDefault Pageable pageable,
                                Model model) {

        PageResponse<GetSubscribeResponseDto> subscribes = service.getSubscribeList(pageable);

        model.addAttribute("content", subscribes.getContent());
        model.addAttribute("next", subscribes.isNext());
        model.addAttribute("previous", subscribes.isPrevious());
        model.addAttribute("totalPage", subscribes.getTotalPages());
        model.addAttribute("pageNum", subscribes.getNumber());
        model.addAttribute("previousPageNo", subscribes.getNumber() - 1);
        model.addAttribute("nextPageNo", subscribes.getNumber() + 1);
        model.addAttribute("size", pageable.getPageSize());
        model.addAttribute("uri", "/admin/subscribes");

        return "admin/subscribe/subscribeList";
    }

    @Auth
    @PostMapping("/admin/subscribes")
    public String subscribeAdd(@ModelAttribute CreateSubscribeRequestDto dto,
                               @RequestPart MultipartFile image) {
        service.addSubscribe(dto, image);

        return RE_SUBSCRIBE;
    }

    @Auth
    @PostMapping("/admin/subscribes/{subscribeNo}")
    public String subscribeModify(@ModelAttribute ModifySubscribeRequestDto dto,
                                  @RequestPart MultipartFile image,
                                  @PathVariable("subscribeNo") Long subscribeNo) {
        service.modifySubscribe(subscribeNo, image, dto);
        return "redirect:/admin/subscribe/" + subscribeNo;
    }


    @GetMapping("/admin/subscribes/{subscribeNo}")
    public String subscribeDetails(@PathVariable("subscribeNo") Long subscribeNo,
                                   Model model) {
        GetSubscribeDetailResponseDto subscribe = service.getSubscribeDetail(subscribeNo);
        model.addAttribute("subscribe", subscribe);
        model.addAttribute("content", subscribe.getProductLists());

        return "/admin/subscribe/subscribesDetail";
    }

    @Auth
    @PostMapping("/admin/subscribes-removed/{subscribeNo}")
    public String subscribeDelete(@PathVariable("subscribeNo") Long subscribeNo,
                                  @RequestParam("deleted") boolean deleted,
                                  Model model) {
        service.deleteSubscribe(subscribeNo, deleted);
        return RE_SUBSCRIBE;
    }

    @Auth
    @PostMapping("/admin/subscribes-renewed/{subscribeNo}")
    public String subscribeRenewed(@PathVariable("subscribeNo") Long subscribeNo,
                                   @RequestParam("renewed") boolean renewed) {
        service.renewedSubscribe(subscribeNo, renewed);
        return RE_SUBSCRIBE;
    }

    @Auth
    @PostMapping("/admin/subscribes-product-list/{subscribeNo}")
    public String subscribeProductList(@PathVariable("subscribeNo") Long subscribeNo,
                                       @RequestParam("products") String products) {
        log.error(products);
        service.addSubscribeProductList(subscribeNo, products);
        return "redirect:/admin/subscribes/" + subscribeNo;
    }

    @GetMapping("/subscribes")
    public String mainSubscribes(@PageableDefault Pageable pageable,
                                 Model model){
        PageResponse<GetSubscribeResponseDto> subscribes = service.getSubscribeList(pageable);
        model.addAttribute("subscribes", subscribes);

        return null;
    }

}
