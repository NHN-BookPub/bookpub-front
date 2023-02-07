package com.nhnacademy.bookpub.bookpubfront.main;

import static com.nhnacademy.bookpub.bookpubfront.state.ProductType.BEST_SELLER;
import static com.nhnacademy.bookpub.bookpubfront.state.ProductType.NEW;

import com.nhnacademy.bookpub.bookpubfront.cart.util.CartUtils;
import com.nhnacademy.bookpub.bookpubfront.category.util.CategoryUtils;
import com.nhnacademy.bookpub.bookpubfront.main.dto.response.GetProductByTypeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import com.nhnacademy.bookpub.bookpubfront.product.service.ProductService;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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

    private final ProductService productService;
    private final MemberUtils memberUtils;
    private final CartUtils cartUtils;
    private final CategoryUtils categoryUtils;
    private static final Integer LIMIT = 6;
    private static final String CART = "CART";


    /**
     * 메인 홈페이지 view.
     *
     * @param cookie   쿠키
     * @param response HttpServletResponse
     * @param model    model
     * @return 메인 홈페이지
     */
    @GetMapping("/")
    public String mainView(@CookieValue(name = CART, required = false) Cookie cookie,
                           HttpServletResponse response,
                           Model model) {
        if (Objects.isNull(cookie)) {
            String uuid = UUID.randomUUID().toString();
            Cookie cartCookie = new Cookie(CART, uuid);
            cartCookie.setMaxAge(86400);
            cartCookie.setPath("/");

            response.addCookie(cartCookie);
        }

        List<GetProductByTypeResponseDto> bestSellers =
                productService.findProductsByType(BEST_SELLER.getTypeNo(), LIMIT);

        List<GetProductByTypeResponseDto> newBooks =
                productService.findProductsByType(NEW.getTypeNo(), LIMIT);

        if (Objects.nonNull(cookie)) {
            cartUtils.getCountInCart(cookie.getValue(), model);
        }

        memberUtils.getMemberNo(model);
        categoryUtils.categoriesView(model);
        model.addAttribute("bestSellers", bestSellers);
        model.addAttribute("newBooks", newBooks);

        return "main/root";
    }
}