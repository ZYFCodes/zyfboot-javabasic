package org.zyf.javabasic.designpatterns.singleton;

/**
 * 描述：屏蔽饿汉式单例模式的内存浪费问题和双重检查锁中synchronized的性能问题
 * 避免因为反射破坏单例
 *
 * @author yanfengzhang
 * @date 2020-01-02 21:08
 */
public class LazyInnerClassSingleton {
    /**
     * 使用LazyInnerClassSingleton的时候会先默认初始化换内部类
     * 如果没有使用，则内部类是不加载的
     */
    private LazyInnerClassSingleton() {
        /*为了避免反射破坏单例，需要在构造方法中增加限制，一旦出现多次重复创建，直接抛出异常*/
        if (null != Lazyholder.LAZY_INNER_CLASS_SINGLETON) {
            throw new RuntimeException("创建LazyInnerClassSingleton异常，不允许创建多个实例！");
        }
    }

    /**
     * 每一个关键字都不是多余的，static是为了使单例的空间共享，保证这个方法不会被重写、重载
     */
    public static final LazyInnerClassSingleton getInstance() {
        /*在返回结果前，一定会先加载内部类*/
        return Lazyholder.LAZY_INNER_CLASS_SINGLETON;
    }

    /**
     * 默认不加载
     */
    private static class Lazyholder {
        private static final LazyInnerClassSingleton LAZY_INNER_CLASS_SINGLETON = new LazyInnerClassSingleton();
    }
}
