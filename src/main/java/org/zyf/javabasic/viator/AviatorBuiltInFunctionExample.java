package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;

/**
 * @program: zyfboot-javabasic
 * @description: 轻松调用内置函数和字符串函数
 * @author: zhangyanfeng
 * @create: 2023-10-21 01:23
 **/
public class AviatorBuiltInFunctionExample {
    public static void main(String[] args) {
        // 调用内置函数
        Number absResult = (Number) AviatorEvaluator.execute("math.abs(-5)");
        System.out.println("Absolute Value: " + absResult); // 输出 Absolute Value: 5.0

        // 5
        System.out.println(AviatorEvaluator.execute("string.length('hello')"));
        //通过string.substring('hello', 1, 2)获取字符串'e', 然后通过函数string.contains判断e是否在'test'中。可以看到, 函数可以嵌套调用。
        System.out.println(AviatorEvaluator.execute("string.contains(\"test\", string.substring('hello', 1, 2))"));
    }
}
