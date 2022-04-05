package org.zyf.javabasic.designpatterns.singleton;

/**
 * 描述：懒汉式单例模式---双重检查锁
 * 相比单锁而言，双重检查锁性能上虽然有提升，但是依旧用到了synchronized关键字总归要上锁，对程序性能还是存在一定的性能影响
 * 不算最优--存在优化空间
 * <p>
 * 建议：如果我们在程序启动后，一定会加载到类，那么用饿汉模式实现的单例简单又实用；
 * 如果我们是写一些工具类，则优先考虑使用懒汉模式，可以避免提前被加载到内存中，占用系统资源。
 *
 * @author yanfengzhang
 * @date 2020-01-02 20:53
 */
public class LazyDoubleCheckSingleton {
    /**
     * volatile 关键字可以保证线程间变量的可见性，还有一个作用就是阻止局部重排序的发生
     */
    private volatile static LazyDoubleCheckSingleton lazyDoubleCheckSingleton = null;

    private LazyDoubleCheckSingleton() {
    }

    public static LazyDoubleCheckSingleton getInstance() {
        if (null == lazyDoubleCheckSingleton) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (null == lazyDoubleCheckSingleton) {
                    lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();
                }
            }
        }
        return lazyDoubleCheckSingleton;
    }
}
