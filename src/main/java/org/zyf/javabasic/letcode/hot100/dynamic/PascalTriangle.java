package org.zyf.javabasic.letcode.hot100.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 杨辉三角（简单）
 * @author: zhangyanfeng
 * @create: 2024-08-22 19:28
 **/
public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        // 遍历每一行
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            // 每一行的第一个和最后一个元素都是 1
            row.add(1);
            // 计算中间的元素
            for (int j = 1; j < i; j++) {
                // 每个元素等于上一行的两个相邻元素之和
                row.add(triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j));
            }
            // 每一行的最后一个元素也是 1
            if (i > 0) {
                row.add(1);
            }
            // 将当前行添加到杨辉三角中
            triangle.add(row);
        }

        return triangle;
    }

    public static void main(String[] args) {
        PascalTriangle pt = new PascalTriangle();
        int numRows = 5;
        List<List<Integer>> result = pt.generate(numRows);
        System.out.println(result);
    }
}
