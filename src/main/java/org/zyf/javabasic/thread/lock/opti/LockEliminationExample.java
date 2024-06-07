package org.zyf.javabasic.thread.lock.opti;

/**
 * @program: zyfboot-javabasic
 * @description: 两个字符串拼接的示例
 * @author: zhangyanfeng
 * @create: 2024-06-08 01:49
 **/
public class LockEliminationExample {
    public static void main(String[] args) {
        long startTime;
        long endTime;

        // Test with StringBuffer
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            concatenateStringBuffer("Hello", "World");
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer Execution Time: " + (endTime - startTime) + " ms");

        // Test with StringBuilder
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            concatenateStringBuilder("Hello", "World");
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder Execution Time: " + (endTime - startTime) + " ms");
    }

    public static String concatenateStringBuffer(String s1, String s2) {
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        sb.append(s2);
        return sb.toString();
    }

    public static String concatenateStringBuilder(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        sb.append(s1);
        sb.append(s2);
        return sb.toString();
    }
}
