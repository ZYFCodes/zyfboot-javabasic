package org.zyf.javabasic.letcode.stack;

import java.util.Stack;

/**
 * @author yanfengzhang
 * @description 给定一个包含加法、减法、乘法和除法的算术表达式字符串，设计一个程序来计算其结果。
 * @date 2023/6/13  23:29
 */
public class ArithmeticExpressionCalculator {

    /**
     *
     * 要计算一个包含加减乘除的算术表达式，可以使用栈（Stack）数据结构来辅助计算。以下是一种常见的解题思路：
     * 	1.	创建两个栈，一个用于存储操作数（Operand Stack），一个用于存储运算符（Operator Stack）。
     * 	2.	从左到右依次遍历表达式的每个字符。
     * 	3.	如果当前字符是数字，则将其解析为操作数，并将操作数入栈到 Operand Stack。
     * 	4.	如果当前字符是运算符，则执行以下步骤：
     * 	•	a. 如果 Operator Stack 为空，或者栈顶运算符是左括号 ‘(’，则将当前运算符入栈到 Operator Stack。
     * 	•	b. 如果当前运算符优先级大于栈顶运算符优先级，或者栈顶运算符是左括号 ‘(’，则将当前运算符入栈到 Operator Stack。
     * 	•	c. 否则，从 Operator Stack 弹出栈顶运算符，从 Operand Stack 弹出两个操作数，进行相应的计算，并将结果入栈到 Operand Stack。重复该步骤直到满足条件 b，然后将当前运算符入栈到 Operator Stack。
     * 	•	注意：在比较运算符优先级时，可以使用一定的约定，例如将加法和减法设定为优先级较低，乘法和除法设定为优先级较高。
     * 	5.	如果当前字符是左括号 ‘(’，则将其入栈到 Operator Stack。
     * 	6.	如果当前字符是右括号 ‘)’，则执行以下步骤：
     * 	•	a. 从 Operator Stack 弹出栈顶运算符，从 Operand Stack 弹出两个操作数，进行相应的计算，并将结果入栈到 Operand Stack。重复该步骤直到遇到左括号 ‘(’，将其从 Operator Stack 弹出并丢弃。
     * 	7.	遍历完表达式后，执行以下步骤：
     * 	•	a. 如果 Operator Stack 不为空，则从 Operator Stack 弹出栈顶运算符，从 Operand Stack 弹出两个操作数，进行相应的计算，并将结果入栈到 Operand Stack。重复该步骤直到 Operator Stack 为空。
     * 	•	b. 最后 Operand Stack 中剩下的唯一元素就是表达式的最终结果。
     * @param expression
     * @return
     */
    public static int calculate(String expression) {
        Stack<Integer> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                // 当前字符是数字，将其解析为操作数并入栈
                // 将字符转换为整数
                int operand = ch - '0';
                while (i + 1 < expression.length()
                        && Character.isDigit(expression.charAt(i + 1))) {
                    // 继续读取多位数字
                    operand = operand * 10 + (expression.charAt(i + 1) - '0');
                    i++;
                }
                operandStack.push(operand);
            } else if (ch == '(') {
                // 当前字符是左括号，入栈到运算符栈
                operatorStack.push(ch);
            } else if (ch == ')') {
                // 当前字符是右括号，执行计算直到遇到左括号
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    calculateTopOperation(operandStack, operatorStack);
                }
                // 弹出左括号 '('
                if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                    operatorStack.pop();
                }
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                // 当前字符是运算符
                while (!operatorStack.isEmpty()
                        && shouldEvaluateTopOperation(ch, operatorStack.peek())) {
                    calculateTopOperation(operandStack, operatorStack);
                }
                operatorStack.push(ch);
            }
        }

        // 执行剩余的计算
        while (!operatorStack.isEmpty()) {
            calculateTopOperation(operandStack, operatorStack);
        }

        // 最后 Operand Stack 中剩下的唯一元素就是表达式的最终结果
        return operandStack.pop();
    }

    private static boolean shouldEvaluateTopOperation(char currentOperator, char topOperator) {
        if (topOperator == '(' || topOperator == ')') {
            return false;
        }
        if ((currentOperator == '*' || currentOperator == '/')
                && (topOperator == '+' || topOperator == '-')) {
            return false;
        }
        return true;
    }

    private static void calculateTopOperation(Stack<Integer> operandStack, Stack<Character> operatorStack) {
        char operator = operatorStack.pop();
        int operand2 = operandStack.pop();
        int operand1 = operandStack.pop();
        int result = 0;
        switch (operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                result = operand1 / operand2;
                break;
        }
        operandStack.push(result);
    }

    public static void main(String[] args) {
        String expression1 = "1+2*5-6/2";
        int result1 = calculate(expression1);
        System.out.println(expression1 + "=" + result1);

        String expression2 = "1 + (2 - 3) + 4";
        int result2 = calculate(expression2);
        System.out.println(expression2 + "=" + result2);
    }
}
