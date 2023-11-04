package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 验证 Aviator 中的 nil 常量
 * @author: zhangyanfeng
 * @create: 2023-10-23 00:50
 **/
public class AviatorNilValidation {
    public static void main(String[] args) {
        // 比较 nil 是否相等
        boolean isNilEqual = (boolean) AviatorEvaluator.execute("nil == nil");
        System.out.println("Is nil equal to nil? " + isNilEqual);  // 输出 "Is nil equal to nil? true"

        // 使用 nil 进行比较运算
        boolean isGreaterThanNil = (boolean) AviatorEvaluator.execute("3 > nil");
        System.out.println("Is 3 greater than nil? " + isGreaterThanNil);  // 输出 "Is 3 greater than nil? true"

        // 字符串与 nil 连接
        String result = (String) AviatorEvaluator.execute("nil + 'test'");
        System.out.println("Result of nil + 'test': " + result);  // 输出 "Result of nil + 'test': nulltest"

        // 使用变量与 nil 比较
        Integer a = 3;

        // 创建 Aviator 表达式环境
        Map<String, Object> env = new HashMap<>();
        env.put("a", a);

        boolean isAEqualToNil = (boolean) AviatorEvaluator.execute("a == nil", env);
        System.out.println("Is variable 'a' equal to nil? " + isAEqualToNil);  // 输出 "Is variable 'a' equal to nil? false"
    }
}
