package org.zyf.javabasic.restful.annotations;

import org.zyf.javabasic.restful.validators.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @program: zyfboot-javabasic
 * @description: 自定义注解 @ValidPhoneNumber
 * @author: zhangyanfeng
 * @create: 2023-10-30 16:25
 **/
@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhoneNumber {
    String message() default "无效的电话号码";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
