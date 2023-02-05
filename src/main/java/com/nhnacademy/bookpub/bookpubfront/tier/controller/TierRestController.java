package com.nhnacademy.bookpub.bookpubfront.tier.controller;

import com.nhnacademy.bookpub.bookpubfront.annotation.Auth;
import com.nhnacademy.bookpub.bookpubfront.tier.service.TierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * tier Rest 통신을 하기위한 컨트롤러 입니다.
 *
 * @author : 유호철
 * @since : 1.0
 **/
@RestController
@RequiredArgsConstructor
@Slf4j
public class TierRestController {
    private final TierService tierService;

    @GetMapping("/tier-check")
    @Auth
    public Boolean tierNameCheck(@RequestParam("tierName") String tierName) {
        return tierService.getTierName(tierName);
    }
}
