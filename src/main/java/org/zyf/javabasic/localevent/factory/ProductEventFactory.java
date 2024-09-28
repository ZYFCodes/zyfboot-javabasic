package org.zyf.javabasic.localevent.factory;

import com.google.common.collect.Maps;
import com.google.common.eventbus.EventBus;
import org.zyf.javabasic.localevent.enums.ProductEventCode;

import java.util.List;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 商品事件工厂
 * @author: zhangyanfeng
 * @create: 2024-04-27 19:40
 **/
public class ProductEventFactory {
    /**
     * 每个key对应一个eventbus
     */
    private static Map<ProductEventCode, EventBus> eventBusMap = Maps.newHashMap();

    /**
     * 每个eventBus有多个监听者
     */
    private static Map<ProductEventCode, List<Object>> changesMap = Maps.newHashMap();

    public static EventBus getByProductEventCode(ProductEventCode productEventCode) {
        return eventBusMap.get(productEventCode);
    }

//    public static void register(ProductEventCode productEventCode,EventBus eventBus) {
//        Assert.notNull(productEventCode, "productEventCode can't be null");
//        eventBusMap.put(productEventCode, eventBus);
//        eventBus.register();
//        changesMap.put(productEventCode)
//    }
}
