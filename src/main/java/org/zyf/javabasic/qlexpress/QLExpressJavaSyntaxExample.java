package org.zyf.javabasic.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * @program: zyfboot-javabasic
 * @description: 基本的 Java 语法和对象操作
 * @author: zhangyanfeng
 * @create: 2023-11-12 21:54
 **/
public class QLExpressJavaSyntaxExample {
    public static void main(String[] args) {
        try {
            ExpressRunner runner = new ExpressRunner();
            DefaultContext<String, Object> context = new DefaultContext<>();

            // 创建一个用户对象
            User user = new User("John", 25);
            context.put("user", user);

            // 使用 Java 语法访问对象属性和调用方法
            executeAndPrint(runner, context, "user.getName()", "Accessing Object Property");
            executeAndPrint(runner, context, "user.getAge() + 5", "Performing Arithmetic with Object Property");

            // 使用 Java 语法进行对象操作
            executeAndPrint(runner, context, "user.age = 30", "Modifying Object Property");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeAndPrint(ExpressRunner runner, DefaultContext<String, Object> context, String expression, String operation) throws Exception {
        // 执行脚本
        Object result = runner.execute(expression, context, null, true, false);

        // 输出结果
        System.out.println(operation + ": " + result);
    }

    // 用户对象类
    static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
