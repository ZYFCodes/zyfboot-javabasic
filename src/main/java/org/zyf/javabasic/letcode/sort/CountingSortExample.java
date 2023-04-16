package org.zyf.javabasic.letcode.sort;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/4/16  12:51
 */
public class CountingSortExample {

    /**
     * 数组中有大量重复元素，如何高效地进行排序？
     */
    public static void countSort(int[] arr, int maxVal) {
        int[] count = new int[maxVal + 1];

        /*统计每个元素的出现次数*/
        for (int num : arr) {
            count[num]++;
        }

        /*将统计结果放置到正确的位置上*/
        int idx = 0;
        for (int i = 0; i <= maxVal; i++) {
            while (count[i] > 0) {
                arr[idx++] = i;
                count[i]--;
            }
        }
    }

    /**
     * 给定一组学生成绩，如何按照成绩范围进行统计？
     */
    public static int[] countSortGrades(int[] grades, int maxGrade) {
        int[] count = new int[maxGrade + 1];

        /*统计每个成绩范围内的学生人数*/
        for (int grade : grades) {
            count[grade]++;
        }

        return count;
    }

    /**
     * 如何对字符串数组按照字母顺序进行排序？
     */
    public static void countSortStrings(String[] arr) {
        /*假设字符串只包含 ASCII 字符*/
        int[] count = new int[256];

        /*统计每个字符的出现次数*/
        for (String str : arr) {
            for (char c : str.toCharArray()) {
                count[c]++;
            }
        }

        /*将统计结果生成排序后的字符串数组*/
        int idx = 0;
        for (int i = 0; i < 256; i++) {
            while (count[i] > 0) {
                arr[idx++] = String.valueOf((char) i);
                count[i]--;
            }
        }
    }

    /**
     * 如何对多个关键字进行排序？
     */
    public static void countSortMultiKeys(int[][] arr, int keyIdx, int maxVal) {
        /*统计每个关键字的出现次数*/
        int[] count = new int[maxVal + 1];
        for (int[] row : arr) {
            count[row[keyIdx]]++;
        }

        /*计算每个关键字在输出数组中的起始索引*/
        for (int i = 1; i <= maxVal; i++) {
            count[i] += count[i - 1];
        }

        /*创建临时数组存储排序结果*/
        int[][] output = new int[arr.length][];
        for (int i = arr.length - 1; i >= 0; i--) {
            output[--count[arr[i][keyIdx]]] = arr[i];
        }

        /*将排序结果复制到原数组*/
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }

    public static void main(String[] args) {
        System.out.println("数组中有大量重复元素，如何高效地进行排序？ 验证测试");
        int[] arr = {5, 2, 9, 1, 5, 6};
        int maxVal = 9;

        System.out.println("排序前的数组：" + Arrays.toString(arr));
        countSort(arr, maxVal);
        System.out.println("排序后的数组：" + Arrays.toString(arr));

        System.out.println("给定一组学生成绩，如何按照成绩范围进行统计？ 验证测试");
        int[] grades = {90, 87, 92, 78, 95, 89, 88, 92, 91, 88};
        int maxGrade = 100;

        int[] count = countSortGrades(grades, maxGrade);

        System.out.println("成绩统计结果：");
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                System.out.println(i + ": " + count[i] + "人");
            }
        }

        System.out.println("如何对字符串数组按照字母顺序进行排序？ 验证测试");
        String[] zimuArr = {"apple", "banana", "cherry", "date", "fig", "grape"};

        System.out.println("排序前的字符串数组：");
        for (String str : zimuArr) {
            System.out.print(str + " ");
        }

        countSortStrings(zimuArr);

        System.out.println("\n排序后的字符串数组：");
        for (String str : zimuArr) {
            System.out.print(str + " ");
        }

        System.out.println("如何对多个关键字进行排序？ 验证测试");
        /*输入数据*/
        int[][] keyWordArr = {{3, 5}, {1, 2}, {2, 3}, {2, 1}, {1, 4}, {3, 6}};

        /*原始数组*/
        System.out.println("原始数组：");
        for (int[] row : keyWordArr) {
            System.out.println(Arrays.toString(row));
        }

        /*调用 countSortMultiKeys 进行排序*/
        countSortMultiKeys(keyWordArr, 0, 3);

        /*排序后的数组*/
        System.out.println("排序后的数组：");
        for (int[] row : keyWordArr) {
            System.out.println(Arrays.toString(row));
        }
    }
}
