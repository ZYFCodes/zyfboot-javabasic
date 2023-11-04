package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 字符串比较
 * @author: zhangyanfeng
 * @create: 2023-10-21 16:38
 **/
public class StringComparisonExample {
    public static void main(String[] args) {
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile("s1 == s2");

        Map<String, Object> env = new HashMap<String, Object>();
        env.put("s1", "Hello");
        env.put("s2", "World");

        // 执行表达式
        Boolean result = (Boolean) compiledExp.execute(env);
        System.out.println(result);  // 输出 false
    }
}
