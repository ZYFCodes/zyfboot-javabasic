package org.zyf.javabasic.letcode.featured75.stack;

import java.util.Stack;

/**
 * @program: zyfboot-javabasic
 * @description: 股票价格跨度
 * @author: zhangyanfeng
 * @create: 2024-08-24 14:14
 **/
public class StockSpanner {
    // 使用栈存储价格和对应的跨度
    private Stack<int[]> stack;

    // 构造函数，初始化栈
    public StockSpanner() {
        stack = new Stack<>();
    }

    public int next(int price) {
        int span = 1;

        // 弹出所有小于等于当前价格的元素，并累加它们的跨度
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }

        // 将当前价格和计算得出的跨度压入栈中
        stack.push(new int[]{price, span});

        // 返回当前价格的跨度
        return span;
    }

    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        System.out.println(stockSpanner.next(100)); // 输出: 1
        System.out.println(stockSpanner.next(80));  // 输出: 1
        System.out.println(stockSpanner.next(60));  // 输出: 1
        System.out.println(stockSpanner.next(70));  // 输出: 2
        System.out.println(stockSpanner.next(60));  // 输出: 1
        System.out.println(stockSpanner.next(75));  // 输出: 4
        System.out.println(stockSpanner.next(85));  // 输出: 6
    }
}
