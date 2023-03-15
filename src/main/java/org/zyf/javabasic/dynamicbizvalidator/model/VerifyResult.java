package org.zyf.javabasic.dynamicbizvalidator.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author yanfengzhang
 * @description 校验结果信息
 * @date 2023/3/9  23:55
 */
@Data
@Builder
public class VerifyResult {
    private boolean illegal;
    private Map<String, String> propertyMessageMap;

    public String getErrorMsg() {
        if (propertyMessageMap != null && propertyMessageMap.size() > 0) {
            return propertyMessageMap.values().toString();
        }
        return "";
    }
}
