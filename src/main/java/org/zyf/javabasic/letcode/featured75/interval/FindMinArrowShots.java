package org.zyf.javabasic.letcode.featured75.interval;

import java.util.Arrays;

/**
 * @program: zyfboot-javabasic
 * @description: 用最少数量的箭引爆气球
 * @author: zhangyanfeng
 * @create: 2024-08-24 14:08
 **/
public class FindMinArrowShots {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        // 按照气球的右边界 xend 进行排序
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        // 初始化箭的数量和当前的射箭位置
        int arrowCount = 1;
        int arrowPos = points[0][1];

        // 遍历所有气球
        for (int i = 1; i < points.length; i++) {
            // 如果当前气球的左边界在当前箭的位置之后，则需要射出新的一箭
            if (points[i][0] > arrowPos) {
                arrowCount++;
                arrowPos = points[i][1];
            }
        }

        return arrowCount;
    }

    public static void main(String[] args) {
        FindMinArrowShots solution = new FindMinArrowShots();

        // 示例测试用例
        int[][] points1 = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        System.out.println(solution.findMinArrowShots(points1)); // 输出: 2

        int[][] points2 = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        System.out.println(solution.findMinArrowShots(points2)); // 输出: 4

        int[][] points3 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        System.out.println(solution.findMinArrowShots(points3)); // 输出: 2
    }
}
