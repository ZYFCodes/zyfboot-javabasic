package org.zyf.javabasic.letcode.sort;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author yanfengzhang
 * @description 对一组具有相同前缀的字符串进行排序
 * 假设有一组字符串，这些字符串都具有相同的前缀，需要按照字符串的字典序进行排序。
 * 可以使用桶排序，以字符串的第一个字符作为排序的关键字，将字符串分配到不同的字符桶中，
 * 再对每个字符桶内的字符串进行排序，最后合并所有桶内的字符串即可。
 * @date 2023/4/16  14:20
 */
public class BucketSortByPrefix {
    public static void bucketSortByPrefix(String[] arr, int prefixLength) {
        if (arr == null || arr.length <= 1 || prefixLength <= 0) {
            return;
        }

        /*创建字符桶*/
        /*假设字符串只包含小写字母，共26个桶*/
        ArrayList<String>[] charBuckets = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            charBuckets[i] = new ArrayList<>();
        }

        /*将字符串分配到字符桶中*/
        for (String str : arr) {
            charBuckets[str.charAt(prefixLength - 1) - 'a'].add(str);
        }

        /*对每个字符桶内的字符串进行排序*/
        int idx = 0;
        for (ArrayList<String> bucket : charBuckets) {
            if (bucket != null && !bucket.isEmpty()) {
                /*对每个字符桶内的字符串进行排序，这里可以使用任何合适的排序算法*/
                Collections.sort(bucket);

                /*将排序后的字符串合并到原数组中*/
                for (String str : bucket) {
                    arr[idx++] = str;
                }
            }
        }
    }

    public static void main(String[] args) {
        /*示例数据*/
        String[] strings = new String[]{"apple", "banana", "bear", "bell", "cat", "dog", "duck"};

        System.out.println("排序前：");
        for (String str : strings) {
            System.out.println(str);
        }

        /*调用桶排序算法进行排序*/
        bucketSortByPrefix(strings, 1);

        System.out.println("\n排序后：");
        for (String str : strings) {
            System.out.println(str);
        }
    }
}
