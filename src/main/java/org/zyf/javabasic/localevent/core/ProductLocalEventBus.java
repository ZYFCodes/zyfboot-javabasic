package org.zyf.javabasic.localevent.core;

import org.zyf.javabasic.localevent.enums.ProductEventCode;

/**
 * @program: zyfboot-javabasic
 * @description: 本地事件总线LocalEventBus
 * @author: zhangyanfeng
 * @create: 2024-04-27 19:22
 **/
public interface ProductLocalEventBus {

    /**
     * 发送事件
     *
     * @param productEventCode
     * @param msg
     */
    void post(ProductEventCode productEventCode, Object msg);
}
