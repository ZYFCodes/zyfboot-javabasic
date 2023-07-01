package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 调整数组顺序使奇数位于偶数前面(一)。
 * 题目要求将一个整数数组中的奇数全部排在偶数的前面，并保持它们的相对顺序不变。
 * @date 2023/6/2  23:04
 */
public class OrderOddEvenArrayI {
    /**
     * 最优的解题思路是使用双指针法。具体步骤如下：
     * 1.	定义两个指针，一个指针left从数组的左侧开始向右移动，另一个指针right从数组的右侧开始向左移动。
     * 2.	当left指针指向偶数而right指针指向奇数时，交换两个指针指向的元素，将奇数放到偶数的前面。
     * 3.	继续移动指针，直到left指针和right指针相遇。
     * 4.	此时，所有的奇数都已经移动到了偶数的前面，并且它们的相对顺序保持不变。
     */
    public void reOrderArray(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        // 左指针
        int left = 0;
        // 右指针
        int right = array.length - 1;

        while (left < right) {
            // 移动左指针直到指向偶数
            while (left < right && array[left] % 2 != 0) {
                left++;
            }

            // 移动右指针直到指向奇数
            while (left < right && array[right] % 2 == 0) {
                right--;
            }

            // 交换左右指针指向的元素
            if (left < right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }
    }

    public static void main(String[] args) {
        OrderOddEvenArrayI solution = new OrderOddEvenArrayI();

        // 测试输入数组
        int[] array = {1, 2, 3, 4, 5, 6, 7};

        // 调用方法调整数组顺序
        solution.reOrderArray(array);

        // 输出调整后的数组
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}
