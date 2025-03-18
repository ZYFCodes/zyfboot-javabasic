package org.zyf.javabasic.extendsdeal;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: zyfboot-javabasic
 * @description: 错误设计：父类开放可重写方法，导致流程泄漏
 * @author: zhangyanfeng
 * @create: 2025-03-09 14:20
 **/
@Slf4j
public class BaseOrderValidator {
    public final boolean validate() {
        checkInventory();     // 核心库存校验
        checkUserStatus();    // 风控校验（❗️可被子类覆盖）
        return true;
    }

    protected void checkInventory() {
        log.info("【Base】库存校验通过");
    }

    protected void checkUserStatus() {
        log.info("【Base】用户风控校验：黑名单、冻结、实名等");
    }
}
