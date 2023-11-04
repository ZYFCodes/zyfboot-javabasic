package org.zyf.javabasic.restful.validators;

import org.zyf.javabasic.restful.annotations.ValidPhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 自定义验证器类 PhoneNumberValidator
 * @author: zhangyanfeng
 * @create: 2023-10-30 16:26
 **/
public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    private static final String PHONE_NUMBER_PATTERN = "^[0-9]+$";

    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
        // 初始化操作，通常不需要在此进行额外的操作
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null) {
            return true;
        }

        return Pattern.matches(PHONE_NUMBER_PATTERN, phoneNumber) && phoneNumber.length() > 8 && phoneNumber.length() < 14;
    }
}
