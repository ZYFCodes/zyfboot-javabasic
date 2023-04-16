package org.zyf.javabasic.letcode.sort;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 对一组身份证号码进行排序
 * 假设有一组身份证号码，需要按照身份证号码进行排序。
 * 可以使用基数排序，以身份证号码的每一位数字作为排序的关键字，从左到右进行排序。
 * @date 2023/4/16  14:52
 */
public class IDNumberRadixSort {
    /**
     * 获取身份证号码的指定位数字
     */
    public static int getDigit(char ch, int d) {
        return Character.getNumericValue(ch) / (int) Math.pow(10, d - 1) % 10;
    }

    /**
     * 使用基数排序对身份证号码进行排序
     */
    public static void radixSort(String[] arr) {
        /*获取身份证号码的最大位数*/
        int maxDigits = 0;
        for (String id : arr) {
            int digits = id.length();
            if (digits > maxDigits) {
                maxDigits = digits;
            }
        }

        /*进行基数排序*/
        for (int d = maxDigits; d >= 1; d--) {
            /*计数数组，用于存储每个位数字出现的次数*/
            int[] count = new int[10];
            /*临时数组，用于存储排序结果*/
            String[] output = new String[arr.length];

            /*统计每个位数字出现的次数*/
            for (String id : arr) {
                if (id.length() >= d) {
                    int digit = getDigit(id.charAt(id.length() - d), 1);
                    count[digit]++;
                }
            }

            /*计算每个位数字的累计次数*/
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            /*将身份证号码按当前位数字放入临时数组中*/
            for (int i = arr.length - 1; i >= 0; i--) {
                if (arr[i].length() >= d) {
                    int digit = getDigit(arr[i].charAt(arr[i].length() - d), 1);
                    output[--count[digit]] = arr[i];
                } else {
                    output[--count[0]] = arr[i];
                }
            }

            /*将临时数组中的结果复制回原数组*/
            System.arraycopy(output, 0, arr, 0, arr.length);
        }
    }

    public static void main(String[] args) {
        String[] arr = {"320583199901010001", "320583199901010002", "320583199901010003", "320583199901010004",
                "320583199901010005"};
        System.out.println("排序前：" + Arrays.toString(arr));
        radixSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

}
