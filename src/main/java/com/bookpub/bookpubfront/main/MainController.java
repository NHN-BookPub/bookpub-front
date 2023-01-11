package com.bookpub.bookpubfront.main;

import com.bookpub.bookpubfront.category.dto.response.GetParentCategoryWithChildrenResponseDto;
import com.bookpub.bookpubfront.category.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class MainController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public String mainView(Model model) {
        List<GetParentCategoryWithChildrenResponseDto> parentCategoryWithChildren =
                categoryService.getParentCategoryWithChildren();
        model.addAttribute("category", parentCategoryWithChildren);

        return "main/index";
    }
}
