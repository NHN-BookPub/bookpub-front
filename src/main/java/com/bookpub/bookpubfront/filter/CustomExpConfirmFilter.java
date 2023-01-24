//package com.bookpub.bookpubfront.filter;
//
//import static com.bookpub.bookpubfront.token.util.JwtUtil.MILL_SEC;
//import com.bookpub.bookpubfront.member.adaptor.MemberAdaptor;
//import com.bookpub.bookpubfront.token.dto.TokenInfoDto;
//import com.bookpub.bookpubfront.token.exception.TokenParsingException;
//import com.bookpub.bookpubfront.token.util.JwtUtil;
//import com.bookpub.bookpubfront.utils.Utils;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.io.IOException;
//import java.time.Duration;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.filter.OncePerRequestFilter;
//
///**
// * jwt의 만료기간을 확인하는 필터.
// *
// * @author : 임태원
// * @since : 1.0
// **/
//@RequiredArgsConstructor
//@Slf4j
//public class CustomExpConfirmFilter extends OncePerRequestFilter {
//    private static final Long RENEW_TIME = Duration.ofMinutes(5).toMillis();
//    private final MemberAdaptor memberAdaptor;
//    private final ObjectMapper objectMapper;
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        Cookie jwtCookie = Utils.findJwtCookie();
//
//        if (Objects.isNull(jwtCookie)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String payload = JwtUtil.decodeJwt(jwtCookie.getValue());
//
//        TokenInfoDto tokenInfo;
//        try {
//            tokenInfo = objectMapper.readValue(payload, TokenInfoDto.class);
//        } catch (JsonProcessingException e) {
//            throw new TokenParsingException();
//        }
//
//        long validTime = tokenInfo.getExp() - (new Date().getTime() / MILL_SEC);
//
//        if (validTime <= RENEW_TIME) {
//            ResponseEntity<Void> result = memberAdaptor.tokenReIssueRequest(jwtCookie.getValue());
//
//            List<String> messages = result.getHeaders().get("X-MESSAGE");
//
//            if (Objects.nonNull(messages)) {
//                log.info(messages.get(0));
//            }
//
//            String accessToken =
//                    Objects.requireNonNull(result.getHeaders().get(JwtUtil.AUTH_HEADER))
//                            .get(0).substring(JwtUtil.TOKEN_TYPE.length());
//            log.warn(accessToken);
//            Long expireTime = Long.parseLong(
//                    Objects.requireNonNull(
//                            result.getHeaders().get(JwtUtil.EXP_HEADER)).get(0)
//            );
//
//            JwtUtil.makeJwtCookie(accessToken, expireTime);
//
//            filterChain.doFilter(request, response);
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//
//}
