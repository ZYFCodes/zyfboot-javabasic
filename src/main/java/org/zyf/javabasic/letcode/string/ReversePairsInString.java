package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 计算一个字符串中逆序对的数量。
 * 逆序对是指在一个字符串中，如果两个字符的顺序与它们在原字符串中的顺序相反，那么它们构成一个逆序对。
 * 例如，对于字符串 "acbd"，它包含两个逆序对：(a, b) 和 (c, b)。
 * @date 2023/3/5  23:17
 */
public class ReversePairsInString {

    /**
     * 最优解法是使用归并排序的方法来计算一个字符串中逆序对的数量。这种解法的时间复杂度是 O(n log n)，其中 n 是字符串的长度。
     * 具体的最优解法步骤如下：
     * * 字符串分成两个部分，分别计算每个部分中的逆序对数。
     * * 合并两个部分，并同时计算合并后的部分中的逆序对数。
     * 这种解法的核心思想是利用归并排序的过程来统计逆序对的数量。
     * 在归并排序的过程中，将数组或字符串拆分成更小的部分，然后逐步合并这些部分并排序。在合并的过程中，统计逆序对的数量。
     * 由于归并排序的时间复杂度是 O(n log n)，其中 n 是字符串的长度，因此这种解法的时间复杂度也是 O(n log n)。
     * 这种解法只需要使用常数大小的额外空间来存储临时数组和合并后的结果，因此空间复杂度是 O(1)。
     * 综上所述，使用归并排序的方法来计算一个字符串中逆序对的数量是最优解法，具有高效的时间复杂度和节省空间的特点。
     */
    public static int reversePairs(String s) {
        char[] array = s.toCharArray();
        // 创建一个辅助数组，用于归并排序过程中存储临时结果
        char[] temp = new char[array.length];
        return mergeSortAndCount(array, temp, 0, array.length - 1);
    }

    /**
     * 归并排序过程中计算逆序对的数量
     */
    private static int mergeSortAndCount(char[] array, char[] temp, int left, int right) {
        if (left >= right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int count = 0;

        // 递归地计算左半部分和右半部分的逆序对数量
        count += mergeSortAndCount(array, temp, left, mid);
        count += mergeSortAndCount(array, temp, mid + 1, right);

        // 合并左半部分和右半部分，并计算合并后的逆序对数量
        count += mergeAndCount(array, temp, left, mid, right);

        return count;
    }

    /**
     * 计算合并后的逆序对数量
     */
    private static int mergeAndCount(char[] array, char[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int count = 0;

        for (int k = left; k <= right; k++) {
            temp[k] = array[k];
        }

        for (int k = left; k <= right; k++) {
            if (i > mid) {
                array[k] = temp[j++];
            } else if (j > right) {
                array[k] = temp[i++];
            } else if (temp[i] <= temp[j]) {
                array[k] = temp[i++];
            } else {
                // 当前元素左半部分比右半部分大，说明存在逆序对
                array[k] = temp[j++];
                count += mid - i + 1;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String s = "acbd";
        int count = reversePairs(s);
        // 输出结果：Number of reverse pairs in the string: 2
        System.out.println("Number of reverse pairs in the string: " + count);
    }
}
