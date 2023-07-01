package org.zyf.javabasic.letcode.jzoffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 0, 1, …, n-1 这 n 个数字排成一个圆圈，
 * 从数字 0 开始每次从这个圆圈中删除第 m 个数字。
 * 求出这个圆圈里剩下的最后一个数字。
 * @date 2023/6/6  22:53
 */
public class LastRemainingInCircle {
    /**
     * 可以使用约瑟夫环问题的解法来解决这个问题。约瑟夫环问题是一个经典的数学问题，其解法可以通过递推公式来得到。
     * 具体步骤如下：
     * 1.	创建一个长度为 n 的列表，用于表示圆圈中的数字。
     * 2.	初始化变量 index 为 0，表示当前要删除的数字的索引。
     * 3.	循环进行以下操作，直到列表中只剩下一个数字：
     * •	计算下一个要删除的数字的索引：(index + m - 1) % n，其中 m 表示每次删除的数字间隔，n 表示当前剩余数字的个数。
     * •	删除列表中对应索引的数字。
     * •	更新 n 的值为 n - 1，表示剩余数字的个数减少了一个。
     * 4.	最终剩下的数字即为所求结果。
     */
    public int lastRemaining(int n, int m) {
        if (n <= 0 || m <= 0) {
            return -1;
        }

        List<Integer> circle = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            circle.add(i);
        }

        int index = 0;
        while (circle.size() > 1) {
            index = (index + m - 1) % circle.size();
            circle.remove(index);
        }

        return circle.get(0);
    }

    public static void main(String[] args) {
        LastRemainingInCircle solution = new LastRemainingInCircle();
        // 测试样例
        int n = 7;
        int m = 3;
        int result = solution.lastRemaining(n, m);
        System.out.println("Last remaining number: " + result);
    }
}
