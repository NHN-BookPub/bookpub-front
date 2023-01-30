package com.nhnacademy.bookpub.bookpubfront.state;

/**
 * 검증할 상태코드 타입입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
public interface States {
    /**
     * 상태코드의 이름을 반환합니다.
     *
     * @return 값을 반환합니다.
     */
    String getName();


    /**
     * 상태코드의 사용여부를 반환합니다.
     *
     * @return 사용여부를 반환합니다.
     */
    boolean isUsed();
}