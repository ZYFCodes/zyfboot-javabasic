package org.zyf.javabasic.extendsdeal;

import lombok.extern.slf4j.Slf4j;
import org.zyf.javabasic.extendsdeal.exception.PaymentException;

/**
 * @program: zyfboot-javabasic
 * @description: 构造方法中不执行可能失败的逻辑
 * @author: zhangyanfeng
 * @create: 2025-03-09 15:34
 **/
@Slf4j
public class BasePaymentNew {
    protected BasePaymentNew() {
        // 构造方法中不执行可能失败的逻辑
        log.info("BasePaymentNew BasePaymentNew BasePaymentNew");
    }

    protected void connectGateway() throws PaymentException {
        // 实际网关连接逻辑
        throw new PaymentException("支付网关连接失败");
    }
}
