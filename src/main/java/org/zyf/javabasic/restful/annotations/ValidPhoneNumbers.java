package org.zyf.javabasic.restful.annotations;

import org.zyf.javabasic.restful.validators.ValidPhoneNumbersValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @program: zyfboot-javabasic
 * @description: 验证电话号码的有效性
 * @author: zhangyanfeng
 * @create: 2023-10-30 16:49
 **/
@Documented
@Constraint(validatedBy = ValidPhoneNumbersValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhoneNumbers {
    String message() default "电话号码无效";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
