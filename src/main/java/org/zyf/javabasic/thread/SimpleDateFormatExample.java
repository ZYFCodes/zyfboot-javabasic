package org.zyf.javabasic.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: zyfboot-javabasic
 * @description: SimpleDateFormat 的案例
 * @author: zhangyanfeng
 * @create: 2024-06-09 13:17
 **/
public class SimpleDateFormatExample {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        Runnable task = () -> {
            String dateString = sdf.format(new Date()); // 格式化日期
            try {
                Date date = sdf.parse(dateString); // 解析日期
                System.out.println(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        };

        // 创建多个线程并启动
        for (int i = 0; i < 5; i++) {
            new Thread(task).start();
        }
    }
}
