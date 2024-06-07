package org.zyf.javabasic.thread.lock.opti;

/**
 * @program: zyfboot-javabasic
 * @description: 锁机制基于线程而不是基于调用
 * @author: zhangyanfeng
 * @create: 2024-06-07 19:04
 **/
public class LockExample {
    public synchronized void a() {
        System.out.println("In method a");
        b();  // 调用 b 方法
    }

    public synchronized void b() {
        System.out.println("In method b");
        c();  // 调用 c 方法
    }

    public synchronized void c() {
        System.out.println("In method c");
    }

    public static void main(String[] args) {
        LockExample example = new LockExample();
        example.a();
    }
}
