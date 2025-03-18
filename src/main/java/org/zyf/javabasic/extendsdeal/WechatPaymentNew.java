package org.zyf.javabasic.extendsdeal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.extendsdeal.exception.PaymentException;

/**
 * @program: zyfboot-javabasic
 * @description: 将异常捕获控制权交给调用方，避免在构造阶段抛出业务异常
 * @author: zhangyanfeng
 * @create: 2025-03-09 15:35
 **/
@Slf4j
@Service
public class WechatPaymentNew extends BasePaymentNew {
    private WechatPaymentNew() {
        super();
    }

    public static WechatPaymentNew createInstance() {
        WechatPaymentNew instance = new WechatPaymentNew();
        try {
            instance.connectGateway(); // ✅ 工厂方法中执行可能失败的逻辑
            return instance;
        } catch (PaymentException e) {
            log.error("微信支付初始化失败，原因: {}", e.getMessage());
            return null; // 或返回降级版本
        }
    }
}
