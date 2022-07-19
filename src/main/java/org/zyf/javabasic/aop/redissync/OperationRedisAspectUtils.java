package org.zyf.javabasic.aop.redissync;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.reflect.CodeSignature;

/**
 * @author yanfengzhang
 * @description 异步同步redis同步切面操作基本处理类
 * @date 2022/7/18  23:23
 */
public class OperationRedisAspectUtils {
    /**
     * 获取参数
     *
     * @param codeSignature
     * @param paramName
     * @return
     */
    public static int fetchParamIndex(CodeSignature codeSignature, String paramName) {
        if (StringUtils.isBlank(paramName)) {
            return -1;
        }

        String[] parameterNames = codeSignature.getParameterNames();
        String eachParameterName;
        for (int i = 0; i < parameterNames.length; i++) {
            eachParameterName = parameterNames[0];
            if (paramName.equals(eachParameterName)) {
                return i;
            }
        }

        return -1;
    }
}
