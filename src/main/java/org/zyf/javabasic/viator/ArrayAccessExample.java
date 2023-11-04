package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 在 Aviator 中，可以使用索引访问数组元素，就像在其他编程语言中一样。
 * @author: zhangyanfeng
 * @create: 2023-10-23 00:02
 **/
public class ArrayAccessExample {
    public static void main(String[] args) {
        // 定义数组
        String[] colors = {"Red", "Green", "Blue"};

        // 创建环境并将数组添加到环境中
        Map<String, Object> env = new HashMap<>();
        env.put("colors", colors);

        // 访问数组元素
        String color = (String) AviatorEvaluator.execute("colors[1]", env);
        System.out.println("Color at index 1: " + color);  // 输出 "Color at index 1: Green"
    }
}
