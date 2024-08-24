package org.zyf.javabasic.letcode.featured75.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 相等行列对
 * @author: zhangyanfeng
 * @create: 2024-08-24 09:10
 **/
public class EqualPairs {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        int count = 0;

        // 使用 HashMap 存储每一行及其出现的次数
        Map<List<Integer>, Integer> rowMap = new HashMap<>();

        // 遍历所有行
        for (int i = 0; i < n; i++) {
            List<Integer> rowList = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                rowList.add(grid[i][j]);
            }
            rowMap.put(rowList, rowMap.getOrDefault(rowList, 0) + 1);
        }

        // 遍历所有列
        for (int j = 0; j < n; j++) {
            List<Integer> colList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                colList.add(grid[i][j]);
            }
            // 如果列的数组在 rowMap 中存在，则增加对应的计数
            if (rowMap.containsKey(colList)) {
                count += rowMap.get(colList);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        EqualPairs solution = new EqualPairs();

        // 测试用例 1
        int[][] grid1 = {{3, 2, 1}, {1, 7, 6}, {2, 7, 7}};
        System.out.println("Test Case 1: " + solution.equalPairs(grid1)); // 应输出 1

        // 测试用例 2
        int[][] grid2 = {{3, 1, 2, 2}, {1, 4, 4, 5}, {2, 4, 2, 2}, {2, 4, 2, 2}};
        System.out.println("Test Case 2: " + solution.equalPairs(grid2)); // 应输出 3
    }
}
