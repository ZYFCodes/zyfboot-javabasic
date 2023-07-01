package org.zyf.javabasic.letcode.jzoffer;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yanfengzhang
 * @description 输入一个非负整数数组，将数组中的数字拼接起来排成一个最小的数。
 * 例如，给定数组 [3, 32, 321]，将其拼接成的最小数为 321323。
 * @date 2023/6/3  22:05
 */
public class PrintMinNumber {
    /**
     * 要将数组中的数字拼接成最小的数，可以使用自定义的比较规则进行排序。
     * 假设有两个数字 x 和 y，如果将它们按照 xy 和 yx 的方式拼接，
     * 若 xy < yx，则将 x 排在 y 前面，否则将 y 排在 x 前面。
     *
     * 具体步骤如下：
     * 	1.	将整数数组转换为字符串数组，便于比较和拼接。
     * 	2.	使用自定义的比较规则对字符串数组进行排序。
     * 	    比较规则为：若 str1 + str2 < str2 + str1，则将 str1 排在 str2 前面。
     * 	3.	将排序后的字符串数组按顺序拼接成一个最小的数。
     * 	4.	返回拼接后的最小数。
     *
     * 该方法的时间复杂度为 O(nlogn)，其中 n 为数组的长度。
     */
    public String printMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }

        // 将整数数组转换为字符串数组
        String[] strs = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strs[i] = String.valueOf(numbers[i]);
        }

        // 自定义比较器进行排序
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String str1 = s1 + s2;
                String str2 = s2 + s1;
                return str1.compareTo(str2);
            }
        });

        // 拼接排序后的字符串数组
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        PrintMinNumber solution = new PrintMinNumber();

        // 测试案例1
        int[] numbers1 = {3, 32, 321};
        String result1 = solution.printMinNumber(numbers1);
        System.out.println("PrintMinNumber([3, 32, 321]) = " + result1);

        // 测试案例2
        int[] numbers2 = {1, 2, 3, 4, 5};
        String result2 = solution.printMinNumber(numbers2);
        System.out.println("PrintMinNumber([1, 2, 3, 4, 5]) = " + result2);
    }
}
