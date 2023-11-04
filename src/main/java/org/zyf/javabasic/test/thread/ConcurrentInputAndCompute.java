package org.zyf.javabasic.test.thread;

import java.util.Scanner;

/**
 * @program: zyfboot-javabasic
 * @description: 需要编写一个程序，该程序在执行密集计算的同时，需要监控标准输入（键盘）以接收用户的输入命令。
 * 但如果获取键盘输入的调用是阻塞的，当没有输入时，它会导致其他逻辑无法执行。
 * 通过多线程的方式来处理这个问题，以确保密集计算和键盘输入的监控能够同时进行。
 * @author: zhangyanfeng
 * @create: 2023-10-21 21:05
 **/
public class ConcurrentInputAndCompute {
    public static void main(String[] args) {
        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                if (scanner.hasNextLine()) {
                    String input = scanner.nextLine();
                    // 在这里解析和执行用户输入的命令
                    System.out.println("Command received: " + input);
                }
            }
        });

        Thread computeThread = new Thread(() -> {
            // 这里执行密集计算任务
            for (int i = 0; i < 1000000; i++) {
                // 模拟密集计算
            }
        });

        inputThread.start();
        computeThread.start();

        try {
            inputThread.join();
            computeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
