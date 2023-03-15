package org.zyf.javabasic.dynamicbizvalidator.dynamicdeal;

import com.google.common.base.Function;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.zyf.javabasic.dynamicbizvalidator.annotation.VerifyKey;
import org.zyf.javabasic.dynamicbizvalidator.annotation.VerifyMessage;
import org.zyf.javabasic.dynamicbizvalidator.model.VerifyResult;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Objects;

/**
 * @author yanfengzhang
 * @description 校验处理整合
 * @date 2023/3/9  23:08
 */
@AllArgsConstructor
@Slf4j
public class ConstraintViolationTransformFunction<T> implements Function<ConstraintViolation<T>, VerifyResult> {

    private Class<T> validClazz;
    private VerifyResult verifyResult;

    @Override
    public VerifyResult apply(ConstraintViolation<T> constraintViolation) {
        Iterator<Path.Node> it = constraintViolation.getPropertyPath().iterator();
        String fieldName = null;
        while (it.hasNext()) {
            fieldName = it.next().getName();
        }
        if (Objects.nonNull(fieldName)) {
            try {
                Field field = validClazz.getDeclaredField(fieldName);
                VerifyMessage verifyMessage = field.getAnnotation(VerifyMessage.class);
                String message = constraintViolation.getMessage();
                if (Objects.nonNull(verifyMessage) && StringUtils.isNotBlank(verifyMessage.value())) {
                    message = verifyMessage.value();
                }

                VerifyKey key = field.getAnnotation(VerifyKey.class);
                String keyValue = (key != null && key.value().length() != 0) ?
                        key.value() :
                        constraintViolation.getPropertyPath().toString();
                verifyResult.getPropertyMessageMap().put(keyValue, message);

                return verifyResult;
            } catch (NoSuchFieldException | SecurityException e) {
                log.warn("Cannot find field " + fieldName, e);
            }
        }
        verifyResult.getPropertyMessageMap().put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        return verifyResult;
    }
}

