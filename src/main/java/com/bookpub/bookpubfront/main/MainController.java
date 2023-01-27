package com.bookpub.bookpubfront.main;

import static com.bookpub.bookpubfront.state.ProductType.BEST_SELLER;
import static com.bookpub.bookpubfront.state.ProductType.NEW;

import com.bookpub.bookpubfront.main.dto.response.GetProductByTypeResponseDto;
import com.bookpub.bookpubfront.product.service.ProductService;
import com.bookpub.bookpubfront.utils.CartUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MainController {

    private final ProductService productService;
    private static final Integer LIMIT = 6;

    /**
     * 메인 화면 View 메서드.
     *
     * @param request HTTP request
     * @param model   view request 보낼 객체
     * @return 메인화면 뷰
     */
    @GetMapping("/")
    public String mainView2(HttpServletRequest request, Model model) {
        List<GetProductByTypeResponseDto> bestSellers =
                productService.findProductsByType(BEST_SELLER.getTypeNo(), LIMIT);
        log.warn("bestSeller : {}", bestSellers);
        List<GetProductByTypeResponseDto> newBooks =
                productService.findProductsByType(NEW.getTypeNo(), LIMIT);
        log.warn("newBooks : {}", newBooks);

        CartUtils.getCountInCart(request, model);

        model.addAttribute("bestSellers", bestSellers);
        model.addAttribute("newBooks", newBooks);


        return "main/root";
    }
}