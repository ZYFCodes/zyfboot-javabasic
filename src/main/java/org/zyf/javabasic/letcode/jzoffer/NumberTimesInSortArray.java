package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 统计一个数字在升序数组中出现的次数。
 * @date 2023/6/3  22:13
 */
public class NumberTimesInSortArray {
    /**
     * 由于数组是升序的，可以利用二分查找的思路来解决这个问题。
     * 	1.	首先使用二分查找找到该数字第一次出现的位置，记为first。
     * 	•	如果数组中不存在该数字，直接返回0。
     * 	•	如果存在，继续执行下一步。
     * 	2.	使用二分查找找到该数字最后一次出现的位置，记为last。
     * 	3.	该数字在数组中出现的次数为last - first + 1。
     *
     * 时间复杂度：O(logN)，其中 N 为数组的长度。
     */
    public int getNumberOfK(int[] array, int k) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int first = binarySearchFirst(array, k);
        int last = binarySearchLast(array, k);

        if (first == -1 || last == -1) {
            return 0;
        }

        return last - first + 1;
    }

    private int binarySearchFirst(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] < target) {
                left = mid + 1;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else {
                if (mid == 0 || array[mid - 1] != target) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    private int binarySearchLast(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] < target) {
                left = mid + 1;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else {
                if (mid == array.length - 1 || array[mid + 1] != target) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        NumberTimesInSortArray solution = new NumberTimesInSortArray();

        int[] array = {1, 2, 3, 3, 3, 4, 5};
        int k = 3;
        int count = solution.getNumberOfK(array, k);
        // 预期输出: 3
        System.out.println("Count: " + count);
    }

}
