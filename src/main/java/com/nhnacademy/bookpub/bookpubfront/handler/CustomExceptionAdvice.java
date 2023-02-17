package com.nhnacademy.bookpub.bookpubfront.handler;

import com.nhnacademy.bookpub.bookpubfront.exception.NotFoundException;
import com.nhnacademy.bookpub.bookpubfront.exception.NotLoginException;
import com.nhnacademy.bookpub.bookpubfront.exception.ServerErrorException;
import com.nhnacademy.bookpub.bookpubfront.handler.exception.*;
import com.nhnacademy.bookpub.bookpubfront.handler.exception.gotoexception.*;
import java.net.ConnectException;
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
public class CustomExceptionAdvice {

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

    @ExceptionHandler(NotFoundException.class)
    public String notFound() {
        return "error/404";
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public String unAuthorized() {
        return "error/401";
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
