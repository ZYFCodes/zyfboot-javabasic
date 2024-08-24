package org.zyf.javabasic.letcode.featured75.binary;

import java.util.Arrays;
import java.util.Random;

/**
 * @program: zyfboot-javabasic
 * @description: 咒语和药水的成功对数
 * @author: zhangyanfeng
 * @create: 2024-08-24 12:30
 **/
public class SuccessfulPairs {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] result = new int[n];

        // 对 potions 数组进行排序
        Arrays.sort(potions);

        // 对每个 spell 使用二分查找
        for (int i = 0; i < n; i++) {
            long minPotion = (success + spells[i] - 1) / spells[i]; // 计算最小的药水强度

            // 使用二分查找找到第一个大于等于 minPotion 的药水
            int left = 0, right = m;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (potions[mid] >= minPotion) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            // 计算符合条件的药水数量
            result[i] = m - left;
        }

        return result;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int n = 10;
        int m = 15;
        int[] spells = new int[n];
        int[] potions = new int[m];
        for (int i = 0; i < n; i++) {
            spells[i] = rand.nextInt(10) + 1;
        }
        for (int i = 0; i < m; i++) {
            potions[i] = rand.nextInt(10) + 1;
        }
        long success = rand.nextInt(100) + 1;

        SuccessfulPairs sol = new SuccessfulPairs();
        int[] result = sol.successfulPairs(spells, potions, success);

        // 打印测试数据
        System.out.println("Spells: " + Arrays.toString(spells));
        System.out.println("Potions: " + Arrays.toString(potions));
        System.out.println("Success: " + success);
        System.out.println("Result: " + Arrays.toString(result));
    }
}
