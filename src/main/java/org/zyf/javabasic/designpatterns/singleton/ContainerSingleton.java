package org.zyf.javabasic.designpatterns.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述：注册式单例模式/登记式单例模式，将每个实例都登记到一个地方，使用唯一的标识获取单例。
 * 注册单例模式有两种：枚举式单例模式+容器式单例模式
 * 建议：容器式单例模式适用于实例非常多的情况，便于管理，但是是非线程安全的。
 *
 * @author yanfengzhang
 * @date 2020-01-03 10:51
 */
public class ContainerSingleton {
    private ContainerSingleton() {
    }

    private static Map<String, Object> ioc = new ConcurrentHashMap<>();

    public static Object getBean(String className) {
        synchronized (ioc) {
            if (ioc.containsKey(className)) {
                return ioc.get(className);
            }
            Object obj = null;
            try {
                obj = Class.forName(className).newInstance();
                ioc.put(className, obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
    }
}
