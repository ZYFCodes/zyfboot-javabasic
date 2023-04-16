package org.zyf.javabasic.letcode.sort;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author yanfengzhang
 * @description 桶排序（Bucket Sort）
 * 将待排序的数据按照一定的范围划分成若干个桶，
 * 每个桶内的数据单独进行排序，可以使用任何一种排序算法，例如插入排序、快速排序等。
 * 然后，将所有的桶按照顺序依次合并，得到最终的排序结果。
 * @date 2023/4/16  14:09
 */
public class BucketSort {
    public static void bucketSort(double[] arr) {
        int n = arr.length;

        /*创建桶*/
        ArrayList<Double>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        /*将元素分配到桶中*/
        for (int i = 0; i < n; i++) {
            int index = (int) (n * arr[i]);
            buckets[index].add(arr[i]);
        }

        /*对每个桶内的元素进行排序*/
        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
        }

        /*将桶中的元素合并到原数组中*/
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);
            }
        }
    }

    public static void main(String[] args) {
        double[] arr = {0.8, 0.2, 0.6, 0.4, 0.1, 0.9, 0.3, 0.5, 0.7};
        System.out.println("原始数组：");
        for (double num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        bucketSort(arr);
        System.out.println("排序后数组：");
        for (double num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}
