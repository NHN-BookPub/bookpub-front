package com.nhnacademy.bookpub.bookpubfront.tier.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.tier.dto.request.CreateTierRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tier.dto.request.ModifyTierRequestDto;
import com.nhnacademy.bookpub.bookpubfront.tier.dto.response.TierResponseDto;
import com.nhnacademy.bookpub.bookpubfront.tier.service.TierService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * 등급 페이지로 갈수있는 컨트롤러입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@Controller
@RequiredArgsConstructor
public class TierController {
    private final TierService tierService;

    /**
     * 관리자가 등급 메인페이지로 갈수있게해준다.
     *
     * @param model the request
     * @return 등급메인
     */
    @GetMapping("/admin/tiers")
    @Auth
    public String tierMain(Model model) {
        List<TierResponseDto> tiers = tierService.getTiers();
        model.addAttribute("tiers", tiers);
        return "admin/tier/tierMain";
    }


    /**
     * 등급을 추가할때쓰이는 폼 POST 이다.
     *
     * @param createTierRequestDto 등급에 들어가야할 정보들이 기입된다.
     * @return 메인페이지로이동.
     * @throws JsonProcessingException objectMapper 발생시키는에러.
     */
    @PostMapping("/admin/tiers")
    @Auth
    public String tierAddForm(@ModelAttribute CreateTierRequestDto createTierRequestDto)
            throws JsonProcessingException {
        tierService.createTier(createTierRequestDto);

        return "redirect:/admin/tiers";
    }

    /**
     * 등급번호를 통해 등급을 조회시 수정페이지로 보냄.
     *
     * @param tierNo 등급번호 기입.
     * @param model  view 로 정보를 보내기위한 request.
     * @return the string
     */
    @GetMapping("/admin/tiers/{tierNo}")
    @Auth
    public String tierModifyForm(@PathVariable("tierNo") Integer tierNo,
                                 Model model) {
        TierResponseDto tier = tierService.getTier(tierNo);
        model.addAttribute("tier", tier);

        return "admin/tier/tierModify";
    }

    /**
     * 등급 수정시 들어오는 PUT.
     *
     * @param modifyTierRequestDto 등급수정시 필요한값이 기입.
     * @return 등급 메인페이지로 이동
     * @throws JsonProcessingException objectMapper 발생시키는에러.
     */
    @PutMapping("/admin/tiers")
    @Auth
    public String tierModify(@ModelAttribute ModifyTierRequestDto modifyTierRequestDto)
            throws JsonProcessingException {
        tierService.modifyTier(modifyTierRequestDto);
        return "redirect:/admin/tiers";
    }
}
