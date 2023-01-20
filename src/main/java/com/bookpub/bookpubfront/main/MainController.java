package com.bookpub.bookpubfront.main;

import static com.bookpub.bookpubfront.state.ProductType.BEST_SELLER;
import static com.bookpub.bookpubfront.state.ProductType.NEW;
import com.bookpub.bookpubfront.category.dto.response.GetParentCategoryWithChildrenResponseDto;
import com.bookpub.bookpubfront.category.service.CategoryService;
import com.bookpub.bookpubfront.main.dto.response.GetProductByTypeResponseDto;
import com.bookpub.bookpubfront.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 메인화면 view 컨트롤러.
 *
 * @author : 유호철
 * @author : 임태원
 * @author : 박경서
 * @author : 여운석
 * @author : 정유진
 * @author : 김서현
 * @since : 1.0
 **/

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private static final Integer LIMIT = 6;

    /**
     * 메인화면에 연결해주는 controller get 메소드.
     *
     * @param model html에 동적인 값을 넘겨주는 객체.
     * @return 메인화면 뷰
     */
    @GetMapping("/")
    public String mainView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GetParentCategoryWithChildrenResponseDto> parentCategoryWithChildren =
                categoryService.getParentCategoryWithChildren();

        String userId = (String) authentication.getPrincipal();

        model.addAttribute("category", parentCategoryWithChildren);
        model.addAttribute("userId", userId);
        return "main/index";
    }

    /**
     * 메인화면 컨트롤러 메서드.
     *
     * @param model 뷰에 보낼 request
     * @return 메인화면 뷰
     */
    @GetMapping("/v1")
    public String mainView2(@PageableDefault Pageable pageable, Model model) {
        List<GetProductByTypeResponseDto> bestSellers =
                productService.findProductsByType(BEST_SELLER.getTypeNo(), LIMIT);
        List<GetProductByTypeResponseDto> newBooks =
                productService.findProductsByType(NEW.getTypeNo(), LIMIT);

        model.addAttribute("bestSellers", bestSellers);
        model.addAttribute("newBooks", newBooks);

        return "main/root";
    }
}