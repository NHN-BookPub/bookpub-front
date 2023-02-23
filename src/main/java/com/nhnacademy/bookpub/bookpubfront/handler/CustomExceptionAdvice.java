package com.nhnacademy.bookpub.bookpubfront.handler;

import com.nhnacademy.bookpub.bookpubfront.exception.NotFoundException;
import com.nhnacademy.bookpub.bookpubfront.exception.NotLoginException;
import com.nhnacademy.bookpub.bookpubfront.exception.ServerErrorException;
import com.nhnacademy.bookpub.bookpubfront.handler.exception.BadGatewayException;
import com.nhnacademy.bookpub.bookpubfront.handler.exception.BadRequestException;
import com.nhnacademy.bookpub.bookpubfront.handler.exception.UnAuthorizedException;
import com.nhnacademy.bookpub.bookpubfront.handler.exception.gotoexception.GoToAdminCategoryException;
import com.nhnacademy.bookpub.bookpubfront.handler.exception.gotoexception.GoToAdminException;
import com.nhnacademy.bookpub.bookpubfront.handler.exception.gotoexception.GoToAdminOrderException;
import com.nhnacademy.bookpub.bookpubfront.handler.exception.gotoexception.GoToAdminTagException;
import com.nhnacademy.bookpub.bookpubfront.handler.exception.gotoexception.GoToAdminTierException;
import com.nhnacademy.bookpub.bookpubfront.handler.exception.gotoexception.GoToMainException;
import com.nhnacademy.bookpub.bookpubfront.member.service.MemberService;
import java.net.ConnectException;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 예외 발생시 에러페이지로 보내주는 Advice입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@ControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionAdvice {
    private final MemberService memberService;

    @ExceptionHandler(GoToMainException.class)
    public String goToMain() {
        return "redirect:/";
    }

    @ExceptionHandler(GoToAdminException.class)
    public String goToAdmin() {
        return "redirect:/admin";
    }

    @ExceptionHandler(GoToAdminTagException.class)
    public String goToAdminAdminTag() {
        return "redirect:/admin/tags";
    }

    @ExceptionHandler(GoToAdminTierException.class)
    public String goToAdminTier() {
        return "redirect:/admin/tiers";
    }

    @ExceptionHandler(GoToAdminCategoryException.class)
    public String goToAdminCategory() {
        return "redirect:/admin/categories";
    }

    @ExceptionHandler(GoToAdminOrderException.class)
    public String goToAdminOrder() {
        return "redirect:/admin/orders/list";
    }

    @ExceptionHandler(NotFoundException.class)
    public String notFound() {
        return "error/404";
    }

    @ExceptionHandler({UnAuthorizedException.class})
    public String unAuthorized(HttpServletResponse response) {
        memberService.logout(response);
        return "redirect:/login";
    }

    @ExceptionHandler({BadRequestException.class, HttpRequestMethodNotSupportedException.class})
    public String other400() {
        return "error/400";
    }

    @ExceptionHandler(NotLoginException.class)
    public String needLogin() {
        return "member/login";
    }

    @ExceptionHandler({ServerErrorException.class})
    public String other500() {
        return "error/500";
    }

    @ExceptionHandler({ConnectException.class, BadGatewayException.class})
    public String badGate() {
        return "error/502";
    }

    @ExceptionHandler(RuntimeException.class)
    public String otherAll() {
        return "error/500";
    }
}
