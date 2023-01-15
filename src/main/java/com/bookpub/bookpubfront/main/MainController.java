package com.bookpub.bookpubfront.main;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 메인화면 view 컨트롤러.
 *
 * @author : 임태원
 * @since : 1.0
 **/

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {
    private final RedisTemplate<String,String> redisTemplate;

    @GetMapping("/")
    public String mainView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String memberUUID = (String) authentication.getPrincipal();
        String sessionId = (String) authentication.getCredentials();
        String userId
                = (String) redisTemplate.opsForHash().get(memberUUID, sessionId);

        model.addAttribute("userId", userId);

        return "main/index";
    }
}
