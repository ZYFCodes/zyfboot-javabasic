package org.zyf.javabasic.letcode.stack;

import java.util.Stack;

/**
 * @author yanfengzhang
 * @description 逆波兰表达式（Reverse Polish Notation，简称RPN），也叫后缀表达式，
 * 是一种数学表达式的写法，其中运算符在操作数的后面。
 * 例如，将中缀表达式 3 + 4 * 2 / (1 - 5) 转换为逆波兰表达式的过程如下：3 4 2 * 1 5 - / +。
 * <p>
 * 给定一个包含正整数、加减乘除符号以及空格的字符串 tokens，请你利用栈的思想，计算该表达式的值。
 * @date 2023/4/8  23:56
 */
public class EvaluateReversePolishNotation {

    /**
     * 最优解法是使用栈数据结构，遍历表达式数组 tokens，
     * 遇到数字则将其压入栈中，遇到运算符则从栈中取出两个数字进行运算，
     * 然后将运算结果再次压入栈中。遍历完表达式数组后，栈顶元素即为表达式的计算结果。
     * <p>
     * 具体算法如下：
     * 初始化一个空栈 stack。
     * 遍历表达式数组 tokens，对于每一个元素 token：
     * 如果 token 是数字，则将其转换为整数并压入栈中。
     * 如果 token 是运算符，则从栈中取出两个数字进行运算，然后将运算结果再次压入栈中。
     * 遍历完表达式数组后，栈顶元素即为表达式的计算结果。
     */
    public int evalRPN(String[] tokens) {
        /*初始化一个空栈*/
        Stack<Integer> stack = new Stack<>();
        /*遍历表达式数组 tokens*/
        for (String token : tokens) {
            /*如果 token 是加号*/
            if (token.equals("+")) {
                /*从栈中取出第二个数字*/
                int num2 = stack.pop();
                /*从栈中取出第一个数字*/
                int num1 = stack.pop();
                /*将两个数字相加，并将运算结果压入栈中*/
                stack.push(num1 + num2);
            }
            /*如果 token 是减号*/
            else if (token.equals("-")) {
                /*从栈中取出第二个数字*/
                int num2 = stack.pop();
                /*从栈中取出第一个数字*/
                int num1 = stack.pop();
                /*将第一个数字减去第二个数字，并将运算结果压入栈中*/
                stack.push(num1 - num2);
            }
            /*如果 token 是乘号*/
            else if (token.equals("*")) {
                /*从栈中取出第二个数字*/
                int num2 = stack.pop();
                /*从栈中取出第一个数字*/
                int num1 = stack.pop();
                /*将两个数字相乘，并将运算结果压入栈中*/
                stack.push(num1 * num2);
            }
            /*如果 token 是除号*/
            else if (token.equals("/")) {
                /*从栈中取出第二个数字*/
                int num2 = stack.pop();
                /*从栈中取出第一个数字*/
                int num1 = stack.pop();
                /*将第一个数字除以第二个数字，并将运算结果压入栈中*/
                stack.push(num1 / num2);
            }
            /*如果 token 是数字*/
            else {
                /*将其转换为整数并压入栈中*/
                stack.push(Integer.parseInt(token));
            }
        }
        /*遍历完表达式数组后，栈顶元素即为表达式的计算结果*/
        return stack.pop();
    }

    public static void main(String[] args) {
        String[] tokens1 = {"2", "1", "+", "3", "*"};
        String[] tokens2 = {"4", "13", "5", "/", "+"};
        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        /*expected output: 9*/
        System.out.println(new EvaluateReversePolishNotation().evalRPN(tokens1));
        /*expected output: 6*/
        System.out.println(new EvaluateReversePolishNotation().evalRPN(tokens2));
        /*expected output: 22*/
        System.out.println(new EvaluateReversePolishNotation().evalRPN(tokens3));
    }
}
