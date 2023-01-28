package com.nhnacademy.bookpub.bookpubfront.state.anno;

import static java.lang.annotation.ElementType.*;

import com.nhnacademy.bookpub.bookpubfront.state.States;
import com.nhnacademy.bookpub.bookpubfront.state.validator.StateCodeValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 상태코드를 검증하는 어노테이션입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Documented
@Target({FIELD, PARAMETER, CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StateCodeValidator.class)
public @interface StateCode {
    /**
     * 검증 실패시 에러 메시지입니다.
     *
     * @return 메시지를 반환합니다.
     */
    String message() default "";

    /**
     * 상황별로 검증하기 위해 사용합니다.
     * validator 내에 사용되지 않으나 본 메소드가 존재하지 않는 경우
     * validator 가 작동하지 않습니다.
     *
     * @return 클래스.
     */
    Class<?>[] groups() default { };

    /**
     * 심각도별로 검증하기 위해 사용합니다.
     * validator 내에 사용되지 않으나 본 메소드가 존재하지 않는 경우
     * validator 가 작동하지 않습니다.
     *
     * @return 클래스.
     */
    Class<? extends Payload>[] payload() default { };

    /**
     * 검증에 사용할 Enum(상태코드)입니다.
     * States 타입의 Enum 클래스를 넣을 수 있습니다.
     *
     * @return States 타입의 Enum 클래스를 반환합니다.
     */
    Class<? extends States> enumClass();
}