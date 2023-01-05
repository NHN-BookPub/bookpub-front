package com.bookpub.bookpubfront;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Some description here
 *
 * @author : 임태원
 * @since : 1.0
 **/

@Controller
public class MainController {

    @GetMapping("/")
    public String mainView() {
        return "index";
    }
}
