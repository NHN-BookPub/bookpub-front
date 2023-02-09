package com.nhnacademy.bookpub.bookpubfront.config;

import com.nhnacademy.bookpub.bookpubfront.interceptor.TokenCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web에 관련된 설정을 하는 클래스.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final TokenCheckInterceptor tokenCheckInterceptor;

    /**
     * 인터셉터를 추가하는 메소드 입니다.
     *
     * @param registry 등록객체.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenCheckInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/error");
    }
}
