package org.zyf.javabasic.letcode.featured75.interval;

import java.util.Arrays;

/**
 * @program: zyfboot-javabasic
 * @description: 无重叠区间
 * @author: zhangyanfeng
 * @create: 2024-08-24 13:58
 **/
public class EraseOverlapIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // 将区间按照结束时间从小到大进行排序
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        // 初始化保留的区间数量，设置第一个区间的结束时间为初始值
        int count = 1;
        int end = intervals[0][1];

        // 遍历区间
        for (int i = 1; i < intervals.length; i++) {
            // 如果当前区间的开始时间大于等于上一个保留区间的结束时间
            if (intervals[i][0] >= end) {
                count++; // 保留当前区间
                end = intervals[i][1]; // 更新结束时间
            }
        }

        // 返回需要移除的区间数量
        return intervals.length - count;
    }

    public static void main(String[] args) {
        EraseOverlapIntervals solution = new EraseOverlapIntervals();

        // 示例测试用例
        int[][] intervals1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println(solution.eraseOverlapIntervals(intervals1)); // 输出: 1

        int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};
        System.out.println(solution.eraseOverlapIntervals(intervals2)); // 输出: 2

        int[][] intervals3 = {{1, 2}, {2, 3}};
        System.out.println(solution.eraseOverlapIntervals(intervals3)); // 输出: 0
    }
}
