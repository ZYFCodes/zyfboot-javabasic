package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 验证跟踪功能
 * @author: zhangyanfeng
 * @create: 2023-10-29 18:23
 **/
public class AviatorDebugExample {
    public static void main(String[] args) {
        AviatorEvaluator.setTraceOutputStream(System.out);

        String expression = "a + b * c";
        Map<String, Object> env = new HashMap<>();
        env.put("a", 5);
        env.put("b", 10);
        env.put("c", 2);

        Object result = AviatorEvaluator.execute(expression, env);
        System.out.println("Result: " + result);
    }
}
