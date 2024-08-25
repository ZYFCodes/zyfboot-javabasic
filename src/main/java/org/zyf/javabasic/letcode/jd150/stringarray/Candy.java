package org.zyf.javabasic.letcode.jd150.stringarray;

import java.util.Arrays;

/**
 * @program: zyfboot-javabasic
 * @description: 分发糖果
 * @author: zhangyanfeng
 * @create: 2024-08-25 09:51
 **/
public class Candy {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];

        // 每个孩子至少给一颗糖果
        Arrays.fill(candies, 1);

        // 从左到右遍历，确保评分更高的孩子获得更多的糖果
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // 从右到左遍历，确保评分更高的孩子获得更多的糖果
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        // 计算糖果总数
        int totalCandies = 0;
        for (int candy : candies) {
            totalCandies += candy;
        }

        return totalCandies;
    }
}
