package org.zyf.javabasic.letcode.jzoffer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description 给定一个整数 n，求满足 a = b + c 的所有可能的组合，
 * 其中 a、b、c 均为 n 位数，且 a、b、c 均由数字集合 {1, 2, …, 9} 中的数字组成。
 * 要求找出所有满足条件的 a=b+c 的组合。
 * @date 2023/5/15  23:07
 */
public class ABCCombinations {

    /**
     * 我们可以使用回溯法来解决这个问题。回溯法是一种通过不断地尝试可能的解决方案，并在不满足条件时回退的方法。
     *
     * 具体的解法如下：
     *
     * 	1.	定义一个递归函数，参数包括当前正在生成的数字 a、当前位数 cur、已经选择的数字集合 usedNums、结果集合 result。
     * 	2.	在递归函数中，首先判断当前位数 cur 是否为 n，如果是，则判断 a 是否满足条件 a = b + c，如果满足则将 a 加入结果集合 result。
     * 	3.	如果当前位数 cur 小于 n，则遍历数字集合 {1, 2, …, 9}，对于每个数字 num，如果 num 没有被使用过，则将其加入已经选择的数字集合 usedNums，然后递归调用函数生成下一位数的数字。
     * 	4.	在递归调用返回后，需要将 num 从已经选择的数字集合 usedNums 中移除，以便生成其他可能的组合。
     * 	5.	最后，返回结果集合 result。
     * @param n
     * @return
     */
    public static List<int[]> findCombinations(int n) {
        // 用于存储满足条件的组合结果
        List<int[]> result = new ArrayList<>();
        // 已经使用的数字集合
        Set<Integer> usedNums = new HashSet<>();
        // 调用递归函数生成组合
        generateCombinations(0, n, usedNums, result);
        return result;
    }

    private static void generateCombinations(int cur, int n, Set<Integer> usedNums, List<int[]> result) {
        if (cur == n) {
            // 生成数字 a
            int a = concatenateDigits(usedNums);
            // 找到满足条件的 b 和 c
            int[] bc = findBC(a);
            if (bc[0] != -1 && bc[1] != -1) {
                // 将组合加入结果集合
                result.add(new int[]{a, bc[0], bc[1]});
            }
            return;
        }

        for (int num = 1; num <= 9; num++) {
            if (!usedNums.contains(num)) {
                // 将数字加入已使用集合
                usedNums.add(num);
                // 递归生成下一位数的数字
                generateCombinations(cur + 1, n, usedNums, result);
                // 回溯，将数字从已使用集合中移除，尝试其他可能的组合
                usedNums.remove(num);
            }
        }
    }

    private static int concatenateDigits(Set<Integer> digits) {
        int result = 0;
        for (int digit : digits) {
            // 拼接数字
            result = result * 10 + digit;
        }
        return result;
    }

    private static int[] findBC(int a) {
        int[] bc = new int[2];
        // 初始化 b 为 -1
        bc[0] = -1;
        // 初始化 c 为 -1
        bc[1] = -1;

        for (int b = 1; b < a; b++) {
            int c = a - b;
            if (isValid(b) && isValid(c)) {
                bc[0] = b;
                bc[1] = c;
                break;
            }
        }

        return bc;
    }

    private static boolean isValid(int num) {
        Set<Integer> digits = new HashSet<>();
        while (num > 0) {
            int digit = num % 10;
            if (digits.contains(digit)) {
                // 数字重复，不满足条件
                return false;
            }
            digits.add(digit);
            num /= 10;
        }
        // 数字不重复，满足条件
        return true;
    }

    public static void main(String[] args) {
        int n = 3;
        List<int[]> combinations = findCombinations(n);
        for (int[] combination : combinations) {
            int a = combination[0];
            int b = combination[1];
            int c = combination[2];
            // 输出满足条件的组合
            System.out.println(a + " = " + b + " + " + c);
        }
    }
}
