package org.zyf.javabasic.restful.validators;

import org.zyf.javabasic.restful.annotations.ValidPhoneNumbers;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 自定义验证器 ValidPhoneNumbersValidator 来验证电话号码的有效性
 * @author: zhangyanfeng
 * @create: 2023-10-30 16:50
 **/
public class ValidPhoneNumbersValidator implements ConstraintValidator<ValidPhoneNumbers, List<String>> {

    @Override
    public void initialize(ValidPhoneNumbers constraintAnnotation) {
        // 初始化操作，通常不需要在此进行额外的操作
    }

    @Override
    public boolean isValid(List<String> phoneNumbers, ConstraintValidatorContext context) {
        if (phoneNumbers == null || phoneNumbers.isEmpty()) {
            // 如果电话号码列表为空，不进行验证
            return true;
        }

        for (String phoneNumber : phoneNumbers) {
            if (!isValidPhoneNumber(phoneNumber)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // 在此执行电话号码的有效性验证逻辑，例如使用正则表达式
        // 此处使用一个简单的示例，验证电话号码是否由数字组成且长度在 10 到 12 位之间
        String phonePattern = "^[0-9]{10,12}$";
        return Pattern.matches(phonePattern, phoneNumber);
    }
}
