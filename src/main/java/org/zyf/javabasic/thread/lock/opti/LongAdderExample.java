package org.zyf.javabasic.thread.lock.opti;

import java.util.concurrent.atomic.LongAdder;

/**
 * @program: zyfboot-javabasic
 * @description: LongAdder在高并发情况下比AtomicInteger有更好的性能
 * @author: zhangyanfeng
 * @create: 2024-06-05 23:26
 **/
public class LongAdderExample {
    private static LongAdder counter = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(new IncrementLongAdder());
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time with LongAdder: " + (endTime - startTime) + " ms");
    }

    static class IncrementLongAdder implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                counter.increment();
            }
        }
    }
}
