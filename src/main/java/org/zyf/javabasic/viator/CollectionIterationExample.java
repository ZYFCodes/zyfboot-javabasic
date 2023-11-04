package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 支持迭代集合元素，例如使用 for 循环
 * @author: zhangyanfeng
 * @create: 2023-10-23 00:14
 **/
public class CollectionIterationExample {
    public static void main(String[] args) {
        // 定义一个 Map
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);

        // 创建环境并将 Map 添加到环境中
        Map<String, Object> env = new HashMap<>();
        env.put("scores", scores);

        // 使用 Java 代码来迭代 Map 并构建结果字符串
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append(" ");
        }

        env.put("result", result.toString());

        // 使用 Aviator 执行表达式
        String script = "result";
        String output = (String) AviatorEvaluator.execute(script, env);

        System.out.println(output);
        // 输出 "Alice: 95 Bob: 87 Charlie: 92 "
    }
}
