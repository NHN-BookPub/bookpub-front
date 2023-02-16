package com.nhnacademy.bookpub.bookpubfront.interceptor;


import static com.nhnacademy.bookpub.bookpubfront.token.util.JwtUtil.JWT_COOKIE;
import static com.nhnacademy.bookpub.bookpubfront.token.util.JwtUtil.MILL_SEC;

import com.nhnacademy.bookpub.bookpubfront.exception.NotLoginException;
import com.nhnacademy.bookpub.bookpubfront.member.adaptor.MemberAdaptor;
import com.nhnacademy.bookpub.bookpubfront.token.util.JwtUtil;
import com.nhnacademy.bookpub.bookpubfront.utils.CookieUtil;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 토큰의 기간을 체크해주는 인터셉터입니다.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class TokenCheckInterceptor implements HandlerInterceptor {
    private static final Long RENEW_TIME = Duration.ofHours(1).toSeconds();
    private static final String ERROR_MESSAGE = "X-MESSAGE";
    private final MemberAdaptor memberAdaptor;

    /**
     * 컨트롤러 진입 전에 실행되는 메소드.
     *
     * @param request  요청
     * @param response 응답
     * @param handler  핸들러
     * @return true, false
     * @throws IOException sendError에서 발생할 수 있는 에러.
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws IOException {

        Cookie jwtCookie = CookieUtil.findCookie(JWT_COOKIE);

        if (unauthorizedAccess(jwtCookie)) {
            return true;
        }

        String cookieValue = Objects.requireNonNull(jwtCookie).getValue();
        String exp = cookieValue.split("\\.")[3];
        String accessToken =
                jwtCookie.getValue().substring(0, accessTokenLength(cookieValue, exp));

        long validTime = getValidTime(exp);

        if (validTime <= RENEW_TIME) {
            ResponseEntity<Void> result = memberAdaptor.tokenReIssueRequest(accessToken);
            List<String> messages = result.getHeaders().get(ERROR_MESSAGE);

            if (isRefreshTokenExpired(response, messages)) {
                log.warn("refreshToken expired");
                throw new NotLoginException();
            }

            return renewAccessToken(request, response, jwtCookie, result);
        }

        requestContainAccessToken(request, accessToken);
        return true;
    }

    /**
     * 커스텀한 exp 부분을 제외한 순수 accessToken만은 길이를 반환해주는 메소드.
     *
     * @param cookieValue 커스텀 한 cookieValue값.
     * @param exp         커스텀하여 추가된 토큰 만료기간.
     * @return 순수 accessToken 길이.
     */
    private static int accessTokenLength(String cookieValue, String exp) {
        return cookieValue.length() - (exp.length() + 1);
    }

    /**
     * 기간이 얼마 남지않은 토큰의 갱신 메소드.
     *
     * @param request   request.
     * @param response  response.
     * @param jwtCookie 갱신하고자 하는 토큰이 저장된 cookie.
     * @param result    갱신시켜주는 auth서버와의 통신 결과.
     * @return 갱신에 성공 했는지 안했는지.
     */
    private static boolean renewAccessToken(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Cookie jwtCookie, ResponseEntity<Void> result) {
        String renewAccessToken = getRenewAccessToken(result);
        Long expireTime = getExpireTime(result);

        updateAccessToken(response, jwtCookie, renewAccessToken, expireTime);
        requestContainAccessToken(request, renewAccessToken);

        return true;
    }

    /**
     * 로그인이 필요한 페이지에 로그인을 하지 않고 들어온 경우 허가되지 않은 접근임을 알려주는 메소드.
     *
     * @param jwtCookie 로그인을 확인할 수 있는 cookie.
     * @return 인가된 사용자인가 아닌가.
     * @throws IOException sendError 하며 발생하는 exception.
     */
    private static boolean unauthorizedAccess(Cookie jwtCookie) {
        return Objects.isNull(jwtCookie);
    }

    /**
     * refreshToken이 만료되었는가 체크하는 메소드.
     *
     * @param response response.
     * @param messages auth서버와의 통신을 통해 refreshToken이 만료되었음을 전달받은 message.
     * @return 만료되었는지 안됐는지.
     * @throws IOException sendError에서 발생하는 exception.
     */
    private static boolean isRefreshTokenExpired(HttpServletResponse response,
                                                 List<String> messages) throws IOException {
        if (Objects.nonNull(messages)) {
            response.sendError(401, messages.get(0));
            return true;
        }
        return false;
    }

    /**
     * 갱신한 토큰을 파싱하는 메소드.
     *
     * @param result auth서버와의 통신 결과.
     * @return accessToken의 파싱 결과.
     */
    private static String getRenewAccessToken(ResponseEntity<Void> result) {
        return Objects.requireNonNull(result.getHeaders().get(JwtUtil.AUTH_HEADER))
                .get(0).substring(JwtUtil.TOKEN_TYPE.length());
    }

    /**
     * 갱신한 토큰의 만료일을 파싱하는 메소드.
     *
     * @param result auth서버와의 통신 결과.
     * @return 파싱 결과.
     */
    private static long getExpireTime(ResponseEntity<Void> result) {
        return Long.parseLong(
                Objects.requireNonNull(
                        result.getHeaders().get(JwtUtil.EXP_HEADER)).get(0)
        );
    }

    /**
     * accessToken을 브라우저상에 갱신해주는 메소드.
     *
     * @param response         response.
     * @param jwtCookie        로그인하면서 발급된 token이 담겨있는 cookie.
     * @param renewAccessToken 갱신된 accessToken.
     * @param expireTime       만료시간.
     */
    private static void updateAccessToken(HttpServletResponse response, Cookie jwtCookie,
                                          String renewAccessToken, Long expireTime) {
        jwtCookie.setValue(renewAccessToken + "." + expireTime);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(7200);
        response.addCookie(jwtCookie);
    }

    /**
     * 토큰의 유효시간을 계산하는 메소드.
     *
     * @param exp 토큰의 만료시간.
     * @return 토큰의 유효시간.
     */
    private static long getValidTime(String exp) {
        long now = new Date().getTime();
        return (Long.parseLong(exp) - now) / MILL_SEC;
    }

    /**
     * request에 token의 정보를 담아주는 메소드.
     *
     * @param request     request.
     * @param accessToken 담고자하는 accessToken 정보.
     */
    private static void requestContainAccessToken(HttpServletRequest request, String accessToken) {
        request.setAttribute(JwtUtil.AUTH_HEADER, JwtUtil.TOKEN_TYPE + accessToken);
    }
}