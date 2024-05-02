package org.zyf.javabasic.localevent.core.impl;

import com.google.common.eventbus.EventBus;
import org.zyf.javabasic.localevent.core.ProductLocalEventBus;
import org.zyf.javabasic.localevent.enums.ProductEventCode;

/**
 * @program: zyfboot-javabasic
 * @description: 商品本地事件码处理
 * @author: zhangyanfeng
 * @create: 2024-04-27 19:25
 **/
public class ProductLocalEventBusImpl implements ProductLocalEventBus {
    /**
     * 发送事件
     *
     * @param eventCode
     * @param msg
     */
    @Override
    public void post(final ProductEventCode eventCode, final Object msg) {
//        long pre = System.currentTimeMillis();
//        try {
//            EventBus eventBus = eventBusMap.get(eventCode.getEventCode());
//            if (eventBus == null) {
//                LoggerUtil.error(LOG, "eventBus is null,eventCode={0}", eventCode.getEventCode());
//                return;
//            }
//            eventBus.post(msg);
//            LoggerUtil.info(LOG, "[EventBusContext] 投递本地消息,{2}ms,Y,eventCode={0},msg={1}",
//                    eventCode, msg, (System.currentTimeMillis() - pre));
//        } catch (Exception e) {
//            LoggerUtil.error(e, LOG, "[EventBusContext] 投递本地消息,{2}ms,N,eventCode={0},msg={1}",
//                    eventCode, msg, (System.currentTimeMillis() - pre));
//        }

    }
}
