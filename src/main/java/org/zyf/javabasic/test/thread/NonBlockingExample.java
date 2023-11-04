package org.zyf.javabasic.test.thread;

/**
 * @program: zyfboot-javabasic
 * @description: 非阻塞
 * @author: zhangyanfeng
 * @create: 2023-10-21 21:48
 **/
public class NonBlockingExample {
    public static void main(String[] args) {
        Runnable nonBlockingTask = () -> {
            System.out.println("Non-blocking task is checking for data...");
            String data = checkForData();
            if (data == null) {
                System.out.println("No data available yet.");
            } else {
                System.out.println("Data received: " + data);
            }
        };

        Thread thread = new Thread(nonBlockingTask);
        thread.start();

        System.out.println("Main thread continues.");
    }

    private static String checkForData() {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 3000) {
            // 模拟数据到达
            if (System.currentTimeMillis() - startTime >= 2000) {
                return "Sample data";
            }
        }
        return null;
    }
}
