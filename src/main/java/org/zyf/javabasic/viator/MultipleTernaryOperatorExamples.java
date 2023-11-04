package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 验证 Aviator 中的三元操作符
 * @author: zhangyanfeng
 * @create: 2023-10-23 00:23
 **/
public class MultipleTernaryOperatorExamples {
    public static void main(String[] args) {
        // 创建一个公共环境
        Map<String, Object> env = new HashMap<>();

        // 示例1
        env.put("a", 1);
        String result1 = (String) AviatorEvaluator.execute("a > 0 ? 'yes' : 'no'", env);
        System.out.println("Example 1: The number " + env.get("a") + " is " + result1);  // 输出 "Example 1: The number 1 is yes"

        // 示例2
        env.put("a", -1);
        String result2 = (String) AviatorEvaluator.execute("a > 0 ? 'yes' : 'no'", env);
        System.out.println("Example 2: The number " + env.get("a") + " is " + result2);  // 输出 "Example 2: The number -1 is no"

        // 示例3
        env.put("a", 0);
        String result3 = (String) AviatorEvaluator.execute("a > 0 ? 'yes' : 'no'", env);
        System.out.println("Example 3: The number " + env.get("a") + " is " + result3);  // 输出 "Example 3: The number 0 is no"
    }
}
