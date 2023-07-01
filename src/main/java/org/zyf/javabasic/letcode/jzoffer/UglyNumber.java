package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。
 * 求按从小到大的顺序的第 N 个丑数。
 * @date 2023/6/3  22:25
 */
public class UglyNumber {
    /**
     * 要找到第 N 个丑数，我们可以从 1 开始逐个判断每个数是否是丑数。
     * 一个数 num 如果满足 num = 2^i * 3^j * 5^k，其中 i、j 和 k 都是非负整数，那么 num 就是丑数。
     * 基于这个思路，我们可以使用动态规划来解决。
     * 我们创建一个数组 ugly 来保存已经找到的丑数，初始化 ugly[0] = 1，
     * 然后我们从 1 开始遍历，每次选择 ugly 中的数乘以 2、3 和 5 中的最小值，
     * 作为下一个丑数，同时更新对应的指针。具体步骤如下：
     * 	1.	创建一个长度为 N 的数组 ugly，并初始化 ugly[0] = 1。
     * 	2.	创建三个指针 p2、p3 和 p5，分别初始化为 0，表示下一个丑数应该乘以 2、3 和 5。
     * 	3.	从 1 开始遍历到 N-1，每次选择 ugly[p2] * 2、ugly[p3] * 3 和 ugly[p5] * 5 中的最小值作为下一个丑数，并更新对应的指针：
     * 	•	如果 ugly[p2] * 2 是最小值，则递增 p2。
     * 	•	如果 ugly[p3] * 3 是最小值，则递增 p3。
     * 	•	如果 ugly[p5] * 5 是最小值，则递增 p5。
     * 	4.	返回 ugly[N-1]，即第 N 个丑数。
     * 这种方法的时间复杂度为 O(N)，空间复杂度为 O(N)。
     */
    public int getUglyNumber(int index) {
        if (index <= 0) {
            return 0;
        }

        int[] ugly = new int[index];
        ugly[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;

        for (int i = 1; i < index; i++) {
            int min = Math.min(ugly[p2] * 2, Math.min(ugly[p3] * 3, ugly[p5] * 5));
            ugly[i] = min;

            if (ugly[p2] * 2 == min) {
                p2++;
            }
            if (ugly[p3] * 3 == min) {
                p3++;
            }
            if (ugly[p5] * 5 == min) {
                p5++;
            }
        }

        return ugly[index - 1];
    }

    public static void main(String[] args) {
        UglyNumber solution = new UglyNumber();

        // 验证例子: 获取第 10 个丑数
        int index = 10;
        int result = solution.getUglyNumber(index);
        System.out.println("第 " + index + " 个丑数为: " + result);
    }

}
