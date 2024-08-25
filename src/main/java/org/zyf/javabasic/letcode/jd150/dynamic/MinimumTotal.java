package org.zyf.javabasic.letcode.jd150.dynamic;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 三角形最小路径和
 * @author: zhangyanfeng
 * @create: 2024-08-25 19:53
 **/
public class MinimumTotal {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        // 从倒数第二行开始，自底向上计算最小路径和
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                // 当前元素加上下一行的两个相邻元素的最小值
                triangle.get(i).set(j, triangle.get(i).get(j) +
                        Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
            }
        }

        // 最顶端元素即为最小路径和
        return triangle.get(0).get(0);
    }
}
