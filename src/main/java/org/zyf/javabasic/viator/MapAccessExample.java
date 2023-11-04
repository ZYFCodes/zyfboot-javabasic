package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 支持访问集合，您可以通过键来访问集合中的元素，就像在使用 Map 一样
 * @author: zhangyanfeng
 * @create: 2023-10-23 00:04
 **/
public class MapAccessExample {
    public static void main(String[] args) {
        // 定义一个 Map
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);

        // 创建环境并将 Map 添加到环境中
        Map<String, Object> env = new HashMap<>();
        env.put("scores", scores);

        // 访问 Map 中的元素
        int aliceScore = (int) AviatorEvaluator.execute("scores['Alice']", env);
        System.out.println("Alice's score: " + aliceScore);  // 输出 "Alice's score: 95"
    }
}
