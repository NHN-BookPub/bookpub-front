package com.bookpub.bookpubfront.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

/**
 * 장바구니를 쿠키로 다루기 위한 Util class.
 *
 * @author : 박경서
 * @since : 1.0
 **/
public class CartUtils {

    private static final String PRODUCT = "product";

    private CartUtils() {
    }

    /**
     * 현재 장바구니에 담긴 상품 갯수를 처리하는 메서드.
     *
     * @param request HttpServletRequest
     * @param model   view 요청을 담을 객체
     */
    public static void getCountInCart(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        Integer count = 0;

        for (Cookie cookie : cookies) {
            if (cookie.getName().contains(PRODUCT)) {
                count++;
            }
        }

        model.addAttribute("cartCount", count);
    }

    /**
     * 주문을 완료한 상품에 관한 쿠키를 지우는 메서드.
     *
     * @param request   HttpServletRequest
     * @param response  HttpServletResponse
     * @param productNo 상품 번호
     */
    public static void deleteCookie(HttpServletRequest request,
                                    HttpServletResponse response,
                                    Long productNo) {
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if (cookieName.contains(PRODUCT)) {
                if (cookie.getValue().equals(productNo)) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }
}
