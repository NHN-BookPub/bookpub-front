package com.bookpub.bookpubfront.main;

import com.bookpub.bookpubfront.category.dto.response.GetParentCategoryWithChildrenResponseDto;
import com.bookpub.bookpubfront.category.service.CategoryService;
import java.util.List;
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
    private final RedisTemplate<String, String> redisTemplate;

    private final CategoryService categoryService;

    /**
     * 메인화면에 연결해주는 controller get메소드.
     *
     * @param model html에 동적인 값을 넘겨주는 객체.
     * @return 메인화면 뷰
     */
    @GetMapping("/")
    public String mainView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GetParentCategoryWithChildrenResponseDto> parentCategoryWithChildren =
                categoryService.getParentCategoryWithChildren();

        String memberUUID = (String) authentication.getPrincipal();
        String sessionId = (String) authentication.getCredentials();
        String userId
                = (String) redisTemplate.opsForHash().get(memberUUID, sessionId);

        model.addAttribute("category", parentCategoryWithChildren);
        model.addAttribute("userId", userId);
        return "main/index";
    }

}
