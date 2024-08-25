package org.zyf.javabasic.letcode.jd150.stringarray;

import java.util.Arrays;

/**
 * @program: zyfboot-javabasic
 * @description: H 指数
 * @author: zhangyanfeng
 * @create: 2024-08-25 09:13
 **/
public class HIndex {
    public int hIndex(int[] citations) {
        // 对数组进行从大到小的排序
        Arrays.sort(citations);

        int n = citations.length;

        // 线性扫描排序后的数组，寻找最大的 h
        for (int i = 0; i < n; i++) {
            int h = n - i;
            // 如果 citations[i] >= h，说明至少有 h 篇论文被引用了至少 h 次
            if (citations[i] >= h) {
                return h;
            }
        }

        // 如果未找到合适的 h，返回 0
        return 0;
    }

    // 测试用例
    public static void main(String[] args) {
        HIndex solution = new HIndex();

        // 示例1
        int[] citations1 = {3, 0, 6, 1, 5};
        System.out.println("h指数: " + solution.hIndex(citations1));  // 输出应为3

        // 示例2
        int[] citations2 = {1, 3, 1};
        System.out.println("h指数: " + solution.hIndex(citations2));  // 输出应为1
    }

}
