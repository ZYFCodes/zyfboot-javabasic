package org.zyf.javabasic.letcode.featured75.stringarray;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 拥有最多糖果的孩子
 * @author: zhangyanfeng
 * @create: 2024-08-23 22:26
 **/
public class KidsWithCandies {
    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = 0;

        // 找到当前糖果数量的最大值
        for (int candy : candies) {
            if (candy > maxCandies) {
                maxCandies = candy;
            }
        }

        List<Boolean> result = new ArrayList<>();

        // 判断每个孩子在得到额外糖果后是否能拥有最多的糖果
        for (int candy : candies) {
            result.add(candy + extraCandies >= maxCandies);
        }

        return result;
    }

    // 测试方法
    public static void main(String[] args) {
        int[] candies1 = {2, 3, 5, 1, 3};
        int extraCandies1 = 3;
        System.out.println(kidsWithCandies(candies1, extraCandies1)); // 输出: [true, true, true, false, true]

        int[] candies2 = {4, 2, 1, 1, 2};
        int extraCandies2 = 1;
        System.out.println(kidsWithCandies(candies2, extraCandies2)); // 输出: [true, false, false, false, false]

        int[] candies3 = {12, 1, 12};
        int extraCandies3 = 10;
        System.out.println(kidsWithCandies(candies3, extraCandies3)); // 输出: [true, false, true]
    }
}
