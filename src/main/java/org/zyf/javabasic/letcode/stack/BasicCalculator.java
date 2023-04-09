package org.zyf.javabasic.letcode.stack;

import java.util.Stack;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/4/9  00:05
 */
public class BasicCalculator {

    /**
     * 使用栈存储数字和运算符，遇到括号则递归计算括号内的表达式。
     * <p>
     * 1 初始化一个栈 stack 存储数字和运算符，和一个变量 res 存储结果，一个变量 sign 存储运算符的正负。
     * 2 遍历表达式 s，如果当前字符是数字，用一个 while 循环读取整个数字并将其压入栈中；
     * 如果是加号，则将正号压入栈中，并将 sign 设为正；
     * 如果是减号，则将负号压入栈中，并将 sign 设为负；
     * 如果是左括号，则递归计算括号内的表达式，并将结果压入栈中；
     * 如果是右括号，则将栈顶的运算符弹出并计算到左括号，弹出左括号。
     * 3 遍历完表达式后，将栈中剩余的数字和运算符依次弹出并计算结果。
     */
    public int calculate(String s) {
        /*数字栈*/
        Stack<Integer> stack = new Stack<>();
        /*当前数字*/
        int num = 0;
        /*当前结果*/
        int result = 0;
        /*符号，1表示加，-1表示减*/
        int sign = 1;
        for (char c : s.toCharArray()) {
            /*如果当前字符是数字*/
            if (Character.isDigit(c)) {
                /*将数字拼接起来*/
                num = num * 10 + (c - '0');
            }
            /*如果当前字符是加号*/
            else if (c == '+') {
                /*先将之前的数字和符号加入结果中*/
                result += sign * num;
                /*重置当前数字*/
                num = 0;
                /*设置符号为加*/
                sign = 1;
            }
            /*如果当前字符是减号*/
            else if (c == '-') {
                /*先将之前的数字和符号加入结果中*/
                result += sign * num;
                /*重置当前数字*/
                num = 0;
                /*设置符号为减*/
                sign = -1;
            }
            /*如果当前字符是左括号*/
            else if (c == '(') {
                /*将当前结果入栈*/
                stack.push(result);
                /*将当前符号入栈*/
                stack.push(sign);
                /*重置当前结果*/
                result = 0;
                /*重置当前符号*/
                sign = 1;
            }
            /*如果当前字符是右括号*/
            else if (c == ')') {
                /*先将当前数字加入结果中*/
                result += sign * num;
                /*重置当前数字*/
                num = 0;
                /*取出栈顶符号乘以当前结果*/
                result *= stack.pop();
                /*加上栈顶数字*/
                result += stack.pop();
            }
        }
        /*处理最后一个数字*/
        if (num != 0) {
            result += sign * num;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "1 + (2 - 3) + 4";
        int result = new BasicCalculator().calculate(s);
        /*输出4*/
        System.out.println(result);
    }


}
