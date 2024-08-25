package org.zyf.javabasic.letcode.jd150.ranges;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 插入区间
 * @author: zhangyanfeng
 * @create: 2024-08-25 12:28
 **/
public class InsertInterval {
    public List<List<Integer>> insertInterval(List<List<Integer>> intervals, List<Integer> newInterval) {
        List<List<Integer>> result = new ArrayList<>();
        int i = 0;
        int n = intervals.size();

        // 添加所有在 newInterval 之前且不重叠的区间
        while (i < n && intervals.get(i).get(1) < newInterval.get(0)) {
            result.add(intervals.get(i++));
        }

        // 合并所有与 newInterval 重叠的区间
        while (i < n && intervals.get(i).get(0) <= newInterval.get(1)) {
            newInterval.set(0, Math.min(newInterval.get(0), intervals.get(i).get(0)));
            newInterval.set(1, Math.max(newInterval.get(1), intervals.get(i).get(1)));
            i++;
        }
        result.add(newInterval);

        // 添加所有在 newInterval 之后且不重叠的区间
        while (i < n) {
            result.add(intervals.get(i++));
        }

        return result;
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // 添加所有在 newInterval 之前且不重叠的区间
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i++]);
        }

        // 合并所有与 newInterval 重叠的区间
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // 添加所有在 newInterval 之后且不重叠的区间
        while (i < n) {
            result.add(intervals[i++]);
        }

        // 将结果转换为 int[][] 并返回
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        InsertInterval sol = new InsertInterval();

        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newInterval1 = {2, 5};

        int[][] intervals2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval2 = {4, 8};

        // 打印输出结果
        printResult(sol.insert(intervals1, newInterval1)); // 输出: [[1, 5], [6, 9]]
        printResult(sol.insert(intervals2, newInterval2)); // 输出: [[1, 2], [3, 10], [12, 16]]
    }

    private static void printResult(int[][] result) {
        for (int[] interval : result) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }
}
