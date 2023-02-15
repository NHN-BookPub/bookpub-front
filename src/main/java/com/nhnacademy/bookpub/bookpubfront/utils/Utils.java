package com.nhnacademy.bookpub.bookpubfront.utils;

import com.nhnacademy.bookpub.bookpubfront.token.util.JwtUtil;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 중복된 메소드를 관리해주는 클래스입니다.
 *
 * @author : 유호철, 임태원
 * @since : 1.0
 */
@Slf4j
public class Utils {
    public static final String AUTHENTICATION = "SPRING_SECURITY_CONTEXT";
    public static final String SESSION_COOKIE = "auth-session";
    public static final String DOMAIN = "domain";

    private Utils() {
    }

    /**
     * 헤더생성에 대한 중복을 방지한 메서드입니다.
     *
     * @return http 헤더가 반환됩니다.
     */
    public static HttpHeaders makeHeader() {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        String accessToken = (String) request.getAttribute(JwtUtil.AUTH_HEADER);
        if (Objects.nonNull(accessToken)) {
            headers.add(JwtUtil.AUTH_HEADER, accessToken);
        }

        return headers;
    }

    /**
     * 헤더생성에 대한 중복을 방지한 메서드입니다.
     *
     * @return http 헤더가 반환됩니다.
     */
    public static HttpHeaders makeHeader(MediaType mediaType) {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setAccept(List.of(mediaType));

        String accessToken = (String) request.getAttribute(JwtUtil.AUTH_HEADER);
        if (Objects.nonNull(accessToken)) {
            headers.add(JwtUtil.AUTH_HEADER, accessToken);
        }

        return headers;
    }

    /**
     * 헤더생성에 대한 중복을 방지한 메서드입니다.
     *
     * @return http 헤더가 반환됩니다.
     */
    public static HttpHeaders makeHeader(MediaType contentType, List<MediaType> accepts) {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(contentType);
        headers.setAccept(accepts);

        String accessToken = (String) request.getAttribute(JwtUtil.AUTH_HEADER);
        if (Objects.nonNull(accessToken)) {
            headers.add(JwtUtil.AUTH_HEADER, accessToken);
        }

        return headers;
    }

    /**
     * String 타입을 받는 List를 SimpleGranted로 변환시켜주는 메소드.
     *
     * @param roles 유저 권한 string list.
     * @return 유저 권한 simplegranted list.
     */
    public static List<SimpleGrantedAuthority> makeAuthorities(List<String> roles) {
        return roles.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * 페이지네이션 유틸.
     *
     * @param model        모델.
     * @param pageResponse 페이지 리스폰스.
     * @param <T>          제네릭타입.
     */
    public static <T> void settingPagination(Model model,
                                             PageResponse<T> pageResponse, String attributeName) {
        model.addAttribute(attributeName, pageResponse.getContent());
        model.addAttribute("totalPages", pageResponse.getTotalPages());
        model.addAttribute("currentPage", pageResponse.getNumber());
        model.addAttribute("isNext", pageResponse.isNext());
        model.addAttribute("isPrevious", pageResponse.isPrevious());
        model.addAttribute("pageButtonNum", 5);
    }
}
