package org.zyf.javabasic.letcode.hot100.ordinaryarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 合并区间
 * @author: zhangyanfeng
 * @create: 2024-08-21 22:13
 **/
public class MergeSolution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }

        // 对区间按起始位置进行排序
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();

        // 遍历排序后的区间列表
        for (int i = 0; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];

            // 如果结果列表为空，或者当前区间与结果列表中最后一个区间不重叠，直接添加当前区间
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < currentInterval[0]) {
                merged.add(currentInterval);
            } else {
                // 合并区间，更新结果列表中最后一个区间的结束位置
                int[] lastMerged = merged.get(merged.size() - 1);
                lastMerged[1] = Math.max(lastMerged[1], currentInterval[1]);
            }
        }

        // 将结果列表转换为二维数组
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        MergeSolution solution = new MergeSolution();

        // 测试用例 1
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result1 = solution.merge(intervals1);
        System.out.println(Arrays.deepToString(result1));  // 输出: [[1, 6], [8, 10], [15, 18]]

        // 测试用例 2
        int[][] intervals2 = {{1, 4}, {4, 5}};
        int[][] result2 = solution.merge(intervals2);
        System.out.println(Arrays.deepToString(result2));  // 输出: [[1, 5]]
    }
}
