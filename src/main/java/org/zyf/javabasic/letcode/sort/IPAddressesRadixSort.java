package org.zyf.javabasic.letcode.sort;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 对一组IP地址进行排序
 * 假设有一组IP地址，需要按照IP地址进行排序。
 * 可以使用基数排序，以IP地址的每一位数字作为排序的关键字，从左到右进行排序。
 * @date 2023/4/16  14:56
 */
public class IPAddressesRadixSort {
    /**
     * 获取IP地址的指定位数字
     */
    public static int getDigit(String ip, int d) {
        String[] segments = ip.split("\\.");
        if (segments.length == 4 && d >= 1 && d <= 4) {
            return Integer.parseInt(segments[d - 1]);
        }
        return -1;
    }

    /**
     * 使用基数排序对IP地址进行排序
     */
    public static void radixSort(String[] arr) {
        /*进行基数排序*/
        for (int d = 4; d >= 1; d--) {
            /*计数数组，用于存储每个位数字出现的次数*/
            int[] count = new int[256];
            /*临时数组，用于存储排序结果*/
            String[] output = new String[arr.length];

            /*统计每个位数字出现的次数*/
            for (String ip : arr) {
                int digit = getDigit(ip, d);
                count[digit]++;
            }

            /*计算每个位数字的累计次数*/
            for (int i = 1; i < 256; i++) {
                count[i] += count[i - 1];
            }

            /*将IP地址按当前位数字放入临时数组中*/
            for (int i = arr.length - 1; i >= 0; i--) {
                int digit = getDigit(arr[i], d);
                output[--count[digit]] = arr[i];
            }

            /*将临时数组中的结果复制回原数组*/
            System.arraycopy(output, 0, arr, 0, arr.length);
        }
    }

    public static void main(String[] args) {
        String[] arr = {"192.168.1.2", "192.168.1.1", "10.0.0.1", "10.0.0.2", "172.16.0.1", "172.16.0.2"};
        System.out.println("排序前：" + Arrays.toString(arr));
        radixSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

}
