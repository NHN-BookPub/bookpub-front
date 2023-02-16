package com.nhnacademy.bookpub.bookpubfront.customerservice.dto;

import com.nhnacademy.bookpub.bookpubfront.state.CustomerServiceCategory;
import com.nhnacademy.bookpub.bookpubfront.state.CustomerServiceState;
import com.nhnacademy.bookpub.bookpubfront.state.anno.StateCode;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

/**
 * 고객서비스 생성을 위한 dto 클래스입니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Getter
@AllArgsConstructor
public class CreateCustomerServiceRequestDto {
    @StateCode(enumClass = CustomerServiceState.class)
    private String customerServiceStateCode;
    @NotNull
    private Long memberNo;
    @StateCode(enumClass = CustomerServiceCategory.class)
    private String serviceCategory;
    @Length(min = 1, max = 100, message = "100자를 넘을 수 없습니다.")
    private String serviceTitle;
    @Length(min = 1, max = 5000, message = "5000자를 넘을 수 없습니다.")
    private String serviceContent;
}
