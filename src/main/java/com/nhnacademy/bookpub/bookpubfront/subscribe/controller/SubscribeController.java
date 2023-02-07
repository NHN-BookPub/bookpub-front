package com.nhnacademy.bookpub.bookpubfront.subscribe.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.request.CreateSubscribeRequestDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.dto.response.GetSubscribeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.subscribe.service.SubscribeService;
import com.nhnacademy.bookpub.bookpubfront.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 구독관련 컨트롤러 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class SubscribeController {
    private final SubscribeService service;

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
        return "redirect:/admin/subscribes";
    }
}
