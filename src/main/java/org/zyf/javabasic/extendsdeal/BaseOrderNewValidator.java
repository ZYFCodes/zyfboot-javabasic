package org.zyf.javabasic.extendsdeal;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: zyfboot-javabasic
 * @description: 模板方法模式，约束子类行为边界
 * @author: zhangyanfeng
 * @create: 2025-03-09 14:53
 **/
@Slf4j
public class BaseOrderNewValidator {
    public final boolean validate() {
        checkInventory();        // 固定的库存检查
        checkUserStatus();       // 固定的风控校验
        validateExtension();     // 提供给子类的扩展点
        return true;
    }

    protected void checkInventory() {
        log.info("【Base】库存校验通过");
    }

    protected void checkUserStatus() {
        log.info("【Base】用户风控校验：黑名单、冻结、实名等");
    }

    protected void validateExtension() {
        // 子类扩展点
        log.info("【Base】子类扩展点");
    }
}
