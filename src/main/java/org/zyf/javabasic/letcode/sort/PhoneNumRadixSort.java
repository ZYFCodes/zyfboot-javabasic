package org.zyf.javabasic.letcode.sort;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 对一组手机号码进行排序
 * 假设有一组手机号码，需要按照手机号码进行排序。
 * 可以使用基数排序，以手机号码的每一位数字作为排序的关键字，从左到右进行排序。
 * @date 2023/4/16  14:43
 */
public class PhoneNumRadixSort {
    /**
     * 获取手机号码的指定位数字
     */
    public static int getDigit(long phoneNum, int d) {
        if (d >= 1 && d <= 11) {
            String numStr = String.valueOf(phoneNum);
            if (numStr.length() >= d) {
                return Integer.parseInt(String.valueOf(numStr.charAt(numStr.length() - d)));
            }
            /*若手机号码不足 d 位，则在高位补0*/
            return 0;
        }
        return -1;
    }

    /**
     * 使用基数排序对手机号码进行排序
     */
    public static void radixSort(long[] arr) {
        /*进行基数排序*/
        for (int d = 11; d >= 1; d--) {
            /*计数数组，用于存储每个位数字出现的次数*/
            int[] count = new int[10];
            /*临时数组，用于存储排序结果*/
            long[] output = new long[arr.length];

            /*统计每个位数字出现的次数*/
            for (long phoneNum : arr) {
                int digit = getDigit(phoneNum, d);
                count[digit]++;
            }

            /*计算每个位数字的累计次数*/
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            /*将手机号码按当前位数字放入临时数组中*/
            for (int i = arr.length - 1; i >= 0; i--) {
                int digit = getDigit(arr[i], d);
                output[--count[digit]] = arr[i];
            }

            /*将临时数组中的结果复制回原数组*/
            System.arraycopy(output, 0, arr, 0, arr.length);
        }
    }

    public static void main(String[] args) {
        long[] arr = {18888888888L, 13333333333L, 17777777777L, 16666666666L, 19999999999L};
        System.out.println("排序前：" + Arrays.toString(arr));
        radixSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }
}
