package org.zyf.javabasic.thread.toolstest.zyf;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/5/2  10:50
 */
public class ZYFLockTest {
    static int count = 0;
    static ZYFLock zyfLock = new ZYFLock();

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    zyfLock.lock();
                    for (int i = 0; i < 10000; i++) {
                        count++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    zyfLock.unlock();
                }

            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count);
    }
}
