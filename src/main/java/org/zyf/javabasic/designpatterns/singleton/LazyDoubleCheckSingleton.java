package org.zyf.javabasic.designpatterns.singleton;

/**
 * 描述：懒汉式单例模式---双重检查锁
 * 相比单锁而言，双重检查锁性能上虽然有提升，但是依旧用到了synchronized关键字总归要上锁，对程序性能还是存在一定的性能影响
 * 不算最优--存在优化空间
 *
 * @author yanfengzhang
 * @date 2020-01-02 20:53
 */
public class LazyDoubleCheckSingleton {
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
