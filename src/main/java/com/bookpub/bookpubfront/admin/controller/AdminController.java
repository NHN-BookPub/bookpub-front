package com.bookpub.bookpubfront.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Admin을 위한 컨트롤러.
 *
 * @author : 박경서
 * @since : 1.0
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String adminIndex() {
        return "admin/index";
    }
}
