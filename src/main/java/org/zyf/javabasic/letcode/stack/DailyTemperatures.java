package org.zyf.javabasic.letcode.stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yanfengzhang
 * @description 给定一个数组 T，求出 T 中每个元素与后面第一个比它大的元素之间的距离。
 * <p>
 * 如果不存在这样的元素，则对应位置的值为 0。
 * 例如，给定数组 T = [73, 74, 75, 71, 69, 72, 76, 73]，应返回 [1, 1, 4, 2, 1, 1, 0, 0]。
 * 提示：每个温度值 T[i] 的范围是 [30, 100]。
 * @date 2023/4/8  22:39
 */
public class DailyTemperatures {

    /**
     * 遍历数组 T，并将每个元素的下标入栈。
     * 当遇到一个比栈顶元素对应的元素大的元素时，
     * 我们可以通过栈顶元素的下标计算出距离，并将其存储到结果数组中。
     * 重复该过程直到遍历完整个数组。
     * <p>
     * 时间复杂度为O(n)，其中n是数组 T 的长度。空间复杂度为O(n)。
     */
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] res = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int j = stack.pop();
                res[j] = i - j;
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] res = new DailyTemperatures().dailyTemperatures(T);
        /*输出 [1, 1, 4, 2, 1, 1, 0, 0]*/
        System.out.println(Arrays.toString(res));
    }
}
