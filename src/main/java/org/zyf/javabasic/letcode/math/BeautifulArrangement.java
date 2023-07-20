package org.zyf.javabasic.letcode.math;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 假设有一个从 1 到 N 的整数数组，如果在数组中的每个 i 处都满足下列两个条件之一，则该数组为漂亮数组：
 * 1. 第 i 位上的数字可以被 i 整除。
 * 2. i 可以被第 i 位上的数字整除。
 * 现在给定一个整数 N，请返回任意一个漂亮数组。
 * <p>
 * 示例 1：输入：N = 4     输出：[2, 1, 4, 3]
 * 示例 2：输入：N = 5     输出：[3, 1, 2, 5, 4]
 * <p>
 * 提示：
 * - 1 <= N <= 1000
 * @date 2023/6/22  23:13
 */
public class BeautifulArrangement {

    /**
     * 最优解法可以通过分治法来实现漂亮数组的生成。
     * 分治法的思想是将问题拆分成更小的子问题，然后将子问题的解合并得到原问题的解。
     * 对于漂亮数组问题，可以将数组拆分成两个子数组，分别满足条件 1 和条件 2。
     * 具体步骤如下：
     * 1. 创建一个数组 result，用于保存漂亮数组。
     * 2. 递归地生成满足条件 1 和条件 2 的两个子数组，分别记为 left 和 right。
     * 3. 将 left 和 right 数组合并，并添加到 result 数组中。
     * 4. 返回 result 数组。
     * 生成满足条件 1 的子数组可以通过对原数组中的偶数索引进行递归生成。
     * 生成满足条件 2 的子数组可以通过对原数组中的奇数索引进行递归生成。
     * 该算法的时间复杂度为 O(NlogN)，其中 N 是输入的整数 N。
     * 因为在每次递归中，数组的长度减半。而空间复杂度为 O(N)，需要额外的数组来保存 result 数组。
     */
    public static int[] beautifulArray(int N) {
        int[] result = new int[N];
        if (N == 1) {
            result[0] = 1;
            return result;
        }

        // 生成满足条件1的子数组，通过对原数组中的偶数索引进行递归生成
        int[] left = beautifulArray((N + 1) / 2);
        // 生成满足条件2的子数组，通过对原数组中的奇数索引进行递归生成
        int[] right = beautifulArray(N / 2);

        // 合并 left 和 right 数组，并添加到 result 数组中
        for (int i = 0; i < left.length; i++) {
            result[i] = 2 * left[i] - 1;
        }
        for (int i = 0; i < right.length; i++) {
            result[i + left.length] = 2 * right[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int N1 = 4;
        int N2 = 5;

        System.out.println("Input: N = " + N1 + " Output: " + Arrays.toString(beautifulArray(N1)));
        System.out.println("Input: N = " + N2 + " Output: " + Arrays.toString(beautifulArray(N2)));
    }
}
