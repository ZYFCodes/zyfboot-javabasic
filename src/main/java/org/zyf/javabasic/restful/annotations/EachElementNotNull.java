package org.zyf.javabasic.restful.annotations;

import org.zyf.javabasic.restful.validators.EachElementNotNullValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @program: zyfboot-javabasic
 * @description: 确保集合中不能含有空元素
 * @author: zhangyanfeng
 * @create: 2023-10-30 17:01
 **/
@Documented
@Constraint(validatedBy = EachElementNotNullValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EachElementNotNull {
    String message() default "集合中含有空元素";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
