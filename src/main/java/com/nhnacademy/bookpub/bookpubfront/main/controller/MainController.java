package com.nhnacademy.bookpub.bookpubfront.main.controller;

import static com.nhnacademy.bookpub.bookpubfront.cart.util.CartUtils.CART_COOKIE;
import com.nhnacademy.bookpub.bookpubfront.cart.util.CartUtils;
import com.nhnacademy.bookpub.bookpubfront.category.util.CategoryUtils;
import com.nhnacademy.bookpub.bookpubfront.main.dto.response.GetProductByTypeResponseDto;
import com.nhnacademy.bookpub.bookpubfront.member.util.MemberUtils;
import com.nhnacademy.bookpub.bookpubfront.order.dto.response.GetOrderAndPaymentResponseDto;
import com.nhnacademy.bookpub.bookpubfront.order.service.OrderService;
import com.nhnacademy.bookpub.bookpubfront.product.service.ProductService;
import com.nhnacademy.bookpub.bookpubfront.state.ProductType;
import com.nhnacademy.bookpub.bookpubfront.utils.CookieUtil;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private final OrderService orderService;
    private final MemberUtils memberUtils;
    private final CartUtils cartUtils;
    private final CategoryUtils categoryUtils;
    private static final Integer LIMIT = 6;


    /**
     * 메인 홈페이지 view.
     *
     * @param response HttpServletResponse
     * @param model    model
     * @param cookie   쿠키
     * @param order    주문.
     * @return 메인 홈페이지
     */
    @GetMapping("/")
    public String mainView(@CookieValue(name = CART_COOKIE, required = false) Cookie cookie,
                           @RequestParam(value = "order", defaultValue = "0") String order,
                           HttpServletResponse response,
                           Model model) {
        if (Objects.isNull(cookie)) {
            CookieUtil.makeCookie(response, CART_COOKIE, UUID.randomUUID().toString());
        }

        List<GetProductByTypeResponseDto> bestSellers =
                productService.findProductsByType(ProductType.BEST_SELLER.getTypeNo(), LIMIT);

        List<GetProductByTypeResponseDto> newBooks =
                productService.findProductsByType(ProductType.NEW.getTypeNo(), LIMIT);

        List<GetProductByTypeResponseDto> recommends =
                productService.findProductsByType(ProductType.RECOMMENDATION.getTypeNo(), LIMIT);

        List<GetProductByTypeResponseDto> populars =
                productService.findProductsByType(ProductType.POPULAR.getTypeNo(), LIMIT);

        List<GetProductByTypeResponseDto> discounts =
                productService.findProductsByType(ProductType.DISCOUNT.getTypeNo(), LIMIT);


        if (Objects.nonNull(cookie)) {
            cartUtils.getCountInCart(cookie.getValue(), model);
        }

        order = getOrderAndPaymentInfo(order, model);

        memberUtils.modelRequestMemberNo(model);
        categoryUtils.categoriesView(model);
        model.addAttribute("bestSellers", bestSellers);
        model.addAttribute("newBooks", newBooks);
        model.addAttribute("recommends", recommends);
        model.addAttribute("populars", populars);
        model.addAttribute("discounts", discounts);

        model.addAttribute("order", order);

        return "main/root";
    }

    /**
     * 주문 정보가 있다면 order에 주문번호, orderInfo에 주문정보를 담습니다.
     *
     * @param order model에 들어갈 변수.
     * @param model model.
     * @return order
     */
    private String getOrderAndPaymentInfo(String order, Model model) {
        if (!order.equals("0")) {
            GetOrderAndPaymentResponseDto orderAndPaymentInfo
                    = orderService.getOrderAndPaymentInfo(order);

            order = verifyOrder(order, orderAndPaymentInfo);

            model.addAttribute("opInfo", orderAndPaymentInfo);
        }
        return order;
    }

    /**
     * 주문정보를 검증하는 메소드.
     *
     * @param order 주문번호.
     * @param orderAndPaymentInfo 주문정보.
     * @return 주문.
     */
    private static String verifyOrder(String order,
                                      GetOrderAndPaymentResponseDto orderAndPaymentInfo) {
        if (Objects.isNull(orderAndPaymentInfo)) {
            order = "0";
        }
        return order;
    }
}