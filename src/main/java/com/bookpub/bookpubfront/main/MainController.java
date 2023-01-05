package com.bookpub.bookpubfront.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 메인화면 view 컨트롤러.
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
