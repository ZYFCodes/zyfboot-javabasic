package org.zyf.javabasic.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @program: zyfboot-javabasic
 * @description: 使用BufferedReader相对于直接使用FileReader可以提供更高的读取速度
 * @author: zhangyanfeng
 * @create: 2024-05-26 17:40
 **/
public class BufferedReaderIOExample {
    private static final String FILE_PATH = "example.txt";

    public static void main(String[] args) {
        long startTime, endTime;

        // 测试直接使用FileReader读取字符
        startTime = System.currentTimeMillis();
        try (FileReader fileReader = new FileReader(FILE_PATH)) {
            int data;
            while ((data = fileReader.read()) != -1) {
                // 模拟处理读取的字符
                // System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        System.out.println("直接使用FileReader读取字符耗时：" + (endTime - startTime) + " 毫秒");

        // 测试使用BufferedReader读取字符
        startTime = System.currentTimeMillis();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // 模拟处理读取的字符
                // System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        System.out.println("使用BufferedReader读取字符耗时：" + (endTime - startTime) + " 毫秒");
    }
}
