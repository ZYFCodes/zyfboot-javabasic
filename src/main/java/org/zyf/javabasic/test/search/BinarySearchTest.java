package org.zyf.javabasic.test.search;

/**
 * @program: zyfboot-javabasic
 * @description: 一般的二分查找的代码实现
 * @author: zhangyanfeng
 * @create: 2024-04-14 11:52
 **/
public class BinarySearchTest {
    // 二分查找函数
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        // 当左边界小于等于右边界时继续查找
        while (left <= right) {
            // 计算中间位置
            int mid = left + (right - left) / 2;

            // 如果目标值等于中间值，则返回中间索引
            if (arr[mid] == target) {
                return mid;
            }

            // 如果目标值小于中间值，则在左半边继续查找
            else if (arr[mid] > target) {
                right = mid - 1;
            }

            // 如果目标值大于中间值，则在右半边继续查找
            else {
                left = mid + 1;
            }
        }

        // 若未找到目标值，则返回 -1 表示不存在
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13};
        int target = 7;
        int index = binarySearch(arr, target);
        if (index != -1) {
            System.out.println("目标值 " + target + " 在数组中的索引为：" + index);
        } else {
            System.out.println("目标值 " + target + " 不存在于数组中。");
        }
    }
}

