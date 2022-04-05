package org.zyf.javabasic.designpatterns.template.check;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description 校验基本返回结构
 * @date 2022/3/16  23:29
 */
@Data
@Builder
public class CheckResponse {
    /**
     * 成功标志：true-校验通过，false-校验未通过
     */
    private boolean pass;
    /**
     * 校验码
     */
    private Integer code;
    /**
     * 未通过原因
     */
    private String errorMsg;
}
