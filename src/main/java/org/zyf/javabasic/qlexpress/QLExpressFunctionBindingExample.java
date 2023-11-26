package org.zyf.javabasic.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * @program: zyfboot-javabasic
 * @description: 可以使用 addFunctionOfClassMethod 和 addFunctionOfServiceMethod 方法来绑定 Java 类或对象的方法
 * @author: zhangyanfeng
 * @create: 2023-11-19 16:13
 **/
public class QLExpressFunctionBindingExample {
    public static void main(String[] args) {
        try {
            ExpressRunner runner = new ExpressRunner();
            DefaultContext<String, Object> context = new DefaultContext<>();

            // 绑定 Math 类的 abs 方法
            runner.addFunctionOfClassMethod("取绝对值", Math.class.getName(), "abs", new String[]{"double"}, null);

            // 绑定 BeanExample 类的 upper 方法
            runner.addFunctionOfClassMethod("转换为大写", BeanExample.class.getName(), "upper", new String[]{"String"}, null);

            // 绑定 System.out 的 println 方法
            runner.addFunctionOfServiceMethod("打印", System.out, "println", new String[]{"String"}, null);

            // 绑定 BeanExample 对象的 anyContains 方法
            runner.addFunctionOfServiceMethod("contains", new BeanExample(), "anyContains", new Class[]{String.class, String.class}, null);

            String express = "取绝对值(-100); 转换为大写(\"hello world\"); 打印(\"你好吗？\"); contains(\"helloworld\", \"aeiou\")";
            Object result = runner.execute(express, context, null, false, false);

            System.out.println("Result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class BeanExample {
        public static double abs(double value) {
            System.out.println("取绝对值结果: " + value);
            return Math.abs(value);
        }

        public static String upper(String abc) {
            System.out.println("转换为大写结果: " + abc.toUpperCase());
            return abc.toUpperCase();
        }

        public boolean anyContains(String str, String searchStr) {
            char[] s = str.toCharArray();
            for (char c : s) {
                if (searchStr.contains(c + "")) {
                    return true;
                }
            }
            return false;
        }
    }
}
