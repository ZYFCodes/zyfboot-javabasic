package org.zyf.javabasic.letcode.array;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 给定一个正整数 n 和一个由正整数组成的数组 A，
 * 您需要找到由数组 A 中的元素（可以重复使用数组 A 中的元素）组成的最大整数，该整数小于 n。
 * 要求编写一个函数来实现这个逻辑。
 * 例如，如果 n 为 23121，数组 A 为 [2, 4, 9]，您需要找到一个由数组 A 中的元素组成的最大整数，该整数小于 23121。
 * 在这种情况下，可能的结果是 22999。
 * @date 2023/8/7  23:00
 */
public class MaxNumberWithArray {

    private int res = 0;

    /**
     * 从数组nums中组合出来一个小于等于n的最大数字并返回.
     * 例如：n = 23131, nums=[2,4,9]
     * 应该返回：22999
     *
     * @param n    目标值
     * @param nums 数组
     * @return 组合出来的小于等于n的最大数字
     */
    private int getCombineMax(int n, int[] nums) {
        // 元素无重复可复选，回溯
        // dfs路径
        StringBuilder path = new StringBuilder();
        // 对数组进行排序，方便后续的遍历
        Arrays.sort(nums);
        // 调用回溯函数
        backtrace(n, nums, 0, path);

        // 回溯没有找到大于等于n的数，取数组中的最大数，组成一个长度等于len(n) - 1的值返回
        if (res == 0) {
            StringBuilder sb = new StringBuilder();
            int count = String.valueOf(n).length();
            while (count > 1) {
                // 取数组中的最大数
                sb.append(nums[nums.length - 1]);
                count--;
            }
            // 将组成的数字转换为整数
            res = Integer.parseInt(sb.toString());
        }
        System.out.printf("当你为某个数n=%d，对应数组是%s，此时输出的最大值为%d\n",
                n, Arrays.toString(nums), res);
        return res;
    }

    /**
     * 深度优先遍历.
     *
     * @param n     目标值
     * @param nums  数组
     * @param start 开始下标
     * @param path  遍历路径
     */
    private void backtrace(int n, int[] nums, int start, StringBuilder path) {
        // 当路径长度和目标值一样长时，是一个结果集，并判断当年结果集是否为小于等于n的最大值，是更新结果集，不是继续
        if (path.length() == String.valueOf(n).length()) {
            // 判断当前路径是否小于等于n
            if (Integer.parseInt(path.toString()) <= n) {
                // 更新结果集
                res = Math.max(res, Integer.parseInt(path.toString()));
            }
            return;
        }

        if (StringUtils.isNotEmpty(path.toString()) && Integer.parseInt(path.toString()) > n) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // 选中当前元素，将当前元素添加到路径中
            path.append(nums[i]);
            // 递递归调用回溯函数
            backtrace(n, nums, i, path);
            // 回溯,删除最后添加的字符
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        int n1 = 23131;
        int[] A1 = {2, 4, 9};
        new MaxNumberWithArray().getCombineMax(n1, A1);

        int n2 = 12121;
        int[] A2 = {2, 4, 9};
        new MaxNumberWithArray().getCombineMax(n2, A2);

        int n3 = 93456;
        int[] A3 = {0, 1, 2, 3, 4, 5};
        new MaxNumberWithArray().getCombineMax(n3, A3);

        int n4 = 12345;
        int[] A4 = {0, 1, 2, 3, 4};
        new MaxNumberWithArray().getCombineMax(n4, A4);

        int n5 = 2345646;
        int[] A5 = {0, 1, 2, 3};
        new MaxNumberWithArray().getCombineMax(n5, A5);

        int n6 = 1122334;
        int[] A6 = {0, 1, 2};
        new MaxNumberWithArray().getCombineMax(n6, A6);

        int n7 = 12345;
        int[] A7 = {0, 1};
        new MaxNumberWithArray().getCombineMax(n7, A7);

        int n8 = 12345;
        int[] A8 = {0};
        new MaxNumberWithArray().getCombineMax(n8, A8);
    }


}
