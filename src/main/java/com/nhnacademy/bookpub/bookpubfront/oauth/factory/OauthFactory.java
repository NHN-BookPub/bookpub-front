package com.nhnacademy.bookpub.bookpubfront.oauth.factory;

import com.nhnacademy.bookpub.bookpubfront.oauth.exception.NotAllowedOauthServiceException;
import com.nhnacademy.bookpub.bookpubfront.oauth.service.GitHubService;
import com.nhnacademy.bookpub.bookpubfront.oauth.service.KakaoService;
import com.nhnacademy.bookpub.bookpubfront.oauth.service.NaverService;
import com.nhnacademy.bookpub.bookpubfront.oauth.service.OauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * Oauth 서비스들의 factory 클래스입니다.
 *
 * @author : 임태원
 * @since : 1.0
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class OauthFactory {
    private final GitHubService gitHubService;
    private final KakaoService kakaoService;
    private final NaverService naverService;

    /**
     * 선택한 버튼에 맞게 OauthService가 생성되는 메소드.
     *
     * @param oauthName 선택한 oauth 버튼 이름.
     * @return 서비스가 존재하면 서비스 아니면 null.
     */
    public OauthService getMatchedService(String oauthName) {
        if (oauthName.equalsIgnoreCase("GITHUB")) {
            return gitHubService;
        } else if (oauthName.equalsIgnoreCase("KAKAO")) {
            return kakaoService;
        } else if (oauthName.equalsIgnoreCase("NAVER")) {
            return naverService;
        } else {
            throw new NotAllowedOauthServiceException();
        }
    }
}
