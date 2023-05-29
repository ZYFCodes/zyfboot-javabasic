package org.zyf.javabasic.cas;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author yanfengzhang
 * @description
 * @date 2019/5/28  22:53
 */
public class NewCasTest {
    private static LongAdder count = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        count.increment();
                    }
                }
            }).start();
        }
        while (Thread.activeCount() > 1) {
        }
        System.out.println(count.sum());
    }
}
