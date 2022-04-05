package org.zyf.javabasic.designpatterns.singleton;

/**
 * 描述：饿汉式单例模式
 * 优点：没有任何锁，执行效率高，用户体验比懒汉式单例模式更好
 * 缺点：类加载的时候就初始化，不管用不用都占内存空间
 * 建议：适用于单例模式较少的场景
 * 如果我们在程序启动后，一定会加载到类，那么用饿汉模式实现的单例简单又实用；
 * 如果我们是写一些工具类，则优先考虑使用懒汉模式，可以避免提前被加载到内存中，占用系统资源。
 *
 * @author yanfengzhang
 * @date 2020-01-02 20:39
 */
public class HungrySingleton {
    private final static HungrySingleton HUNGRY_SINGLETON = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return HUNGRY_SINGLETON;
    }
}
