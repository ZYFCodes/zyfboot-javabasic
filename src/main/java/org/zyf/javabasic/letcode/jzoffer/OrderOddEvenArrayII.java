package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 调整数组顺序使奇数位于偶数前面(二)。
 * 题目要求在原有的奇数位于偶数前面的基础上，
 * 要求奇数之间和偶数之间的相对顺序保持不变，并且奇数之间的相对顺序也保持不变。
 * @date 2023/6/2  23:12
 */
public class OrderOddEvenArrayII {
    /**
     * 最优的解题思路是使用插入排序的思想。具体步骤如下：
     * 1.	定义一个辅助数组evenArray，用于存放原数组中的偶数。
     * 2.	遍历原数组，将所有偶数按照它们在原数组中的顺序依次存放到evenArray中。
     * 3.	遍历原数组，将所有奇数按照它们在原数组中的顺序依次存放到原数组中的空白位置。
     * 4.	最后将evenArray中的偶数按照它们在evenArray中的顺序依次存放到原数组中剩余的空白位置。
     */
    public void reOrderArray(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        // 存放偶数的辅助数组
        int[] evenArray = new int[array.length];
        // 偶数计数
        int evenCount = 0;

        // 遍历原数组，将偶数存放到oddArray中
        for (int num : array) {
            if (num % 2 == 0) {
                evenArray[evenCount++] = num;
            }
        }

        // 遍历原数组，将奇数按顺序存放到原数组中的空白位置
        // 原数组的索引
        int index = 0;
        for (int num : array) {
            if (num % 2 != 0) {
                array[index++] = num;
            }
        }

        // 将oddArray中的偶数按顺序存放到原数组中剩余的空白位置
        for (int i = 0; i < evenCount; i++) {
            array[index++] = evenArray[i];
        }
    }

    public static void main(String[] args) {
        OrderOddEvenArrayII solution = new OrderOddEvenArrayII();

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
