package org.zyf.javabasic.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @program: zyfboot-javabasic
 * @description: 使用BufferedWriter相对于直接使用FileWriter可以提供更高的写入速度
 * @author: zhangyanfeng
 * @create: 2024-05-26 17:28
 **/
public class BufferedWriterIOExample {
    private static final String FILE_PATH = "example.txt";
    private static final String CONTENT = "This is a test content. ";

    public static void main(String[] args) {
        long startTime, endTime;

        // 测试直接使用FileWriter写入字符
        startTime = System.currentTimeMillis();
        try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
            for (int i = 0; i < 100000; i++) {
                fileWriter.write(CONTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        System.out.println("直接使用FileWriter写入字符耗时：" + (endTime - startTime) + " 毫秒");

        // 测试使用BufferedWriter写入字符
        startTime = System.currentTimeMillis();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < 1000000; i++) {
                bufferedWriter.write(CONTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        System.out.println("使用BufferedWriter写入字符耗时：" + (endTime - startTime) + " 毫秒");
    }
}
