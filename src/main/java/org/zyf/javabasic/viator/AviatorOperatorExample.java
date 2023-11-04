package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 使用和比较运算符的多种情况
 * @author: zhangyanfeng
 * @create: 2023-10-21 15:43
 **/
public class AviatorOperatorExample {
    public static void main(String[] args) {
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile("a - (b - c) > 100 && s1 == s2 && i1 >= i2");

        Map<String, Object> env = new HashMap<String, Object>();
        env.put("a", 100.3);
        env.put("b", 45);
        env.put("c", -199.100);
        env.put("s1", "Hello");
        env.put("s2", "Hello");
        env.put("i1", 5);
        env.put("i2", 2);

        // 执行表达式
        Boolean result = (Boolean) compiledExp.execute(env);
        System.out.println(result);  // 输出 true
    }
}
