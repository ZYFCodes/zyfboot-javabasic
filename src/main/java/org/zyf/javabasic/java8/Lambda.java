package org.zyf.javabasic.java8;

/**
 * 描述：Lambda表达式基本学习和练习
 * Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。
 * Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。
 * 使用 Lambda 表达式可以使代码变的更加简洁紧凑。
 *
 * @author yanfengzhang
 * @date 2019-11-26 14:20
 */
public class Lambda {
    final static String salutation = "Hello! ";

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    public static void main(String[] args) {
        final int num = 1;

        Lambda lambdaTest = new Lambda();

        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> { return a * b; };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b ;

        // 访问外层局部变量
        MathOperation getNewInt = ( a,  b) -> a / b + num;

        // 不用括号
        GreetingService greetService1 = message -> System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

        GreetingService greetService3 = message -> System.out.println(salutation + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
        greetService3.sayMessage("Runoob");

        System.out.println("10 + 5 = " + addition.operation(10, 5));
        System.out.println("10 - 5 = " + subtraction.operation(10, 5));
        System.out.println("10 x 5 = " + multiplication.operation(10, 5));
        System.out.println("10 / 5 = " + division.operation(10, 5));
        System.out.println("10 / 5 + 1= " + getNewInt.operation(10, 5));

    }

}
