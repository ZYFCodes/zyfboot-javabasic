package org.zyf.javabasic.restful.validators;

import org.zyf.javabasic.restful.annotations.EachElementNotNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

/**
 * @program: zyfboot-javabasic
 * @description: 验证集合中的元素是否都不为空
 * @author: zhangyanfeng
 * @create: 2023-10-30 17:01
 **/
public class EachElementNotNullValidator implements ConstraintValidator<EachElementNotNull, Collection<?>> {

    @Override
    public void initialize(EachElementNotNull constraintAnnotation) {
        // 初始化操作，通常不需要在此进行额外的操作
    }

    @Override
    public boolean isValid(Collection<?> elements, ConstraintValidatorContext context) {
        if (elements == null || elements.isEmpty()) {
            // 如果集合为空，认为验证通过
            return true;
        }

        for (Object element : elements) {
            if (element == null) {
                return false;
            }
        }

        return true;
    }
}
