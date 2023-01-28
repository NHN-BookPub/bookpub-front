package com.nhnacademy.bookpub.bookpubfront.state.validator;

import com.nhnacademy.bookpub.bookpubfront.state.States;
import com.nhnacademy.bookpub.bookpubfront.state.anno.StateCode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 존재하는 상태코드인지 검증하는 validator 입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
public class StateCodeValidator implements ConstraintValidator<StateCode, String> {
    private List<String> names;

    /**
     * 검증할 값을 설정합니다.
     *
     * @param constraintAnnotation 어노테이션에 enumClass를 받아옵니다.
     */
    @Override
    public void initialize(StateCode constraintAnnotation) {
        names = Arrays.stream(constraintAnnotation.enumClass().getEnumConstants())
                .map(States::getName).collect(Collectors.toList());
    }

    /**
     * 객체의 값이 Enum class에 정의돼있는 값인지 비교하여 검증합니다.
     *
     * @param value 검증당하는 객체.
     * @param context 검증기.
     *
     * @return 검증 성공시 true, 실패시 false 반환.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        for (String name : names) {
            if (name.equals(value)) {
                return true;
            }
        }
        return false;
    }
}