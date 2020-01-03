package org.zyf.javabasic.designpatterns.singleton;

import java.io.Serializable;

/**
 * 描述：如果序列化的目标对象为单例对象，那么就需要避免反序列化破坏单例
 * 需要添加readResolve方法
 *
 * @author yanfengzhang
 * @date 2020-01-03 09:43
 */
public class SeriableSingleton implements Serializable {

    final static SeriableSingleton SERIABLE_SINGLETON = new SeriableSingleton();

    private SeriableSingleton() {
    }

    public static SeriableSingleton getInstance() {
        return SERIABLE_SINGLETON;
    }

    /**
     * 通过JDK源码分析可以看出，虽然增加readResolve（）方法返回实例解决了单例模式被破坏的问题，
     * 但实际上实例化了两次，只不过新的创建对象没有返回而已！
     * 如果创建对象的动作发生频率加快，就意味着内存分配开销也会随之增大，面对需要序列化的情况，建议注册式单例模式
     */
    private Object readResolve() {
        return SERIABLE_SINGLETON;
    }
}

