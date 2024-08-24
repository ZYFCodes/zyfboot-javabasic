package org.zyf.javabasic.letcode.featured75.binary;

/**
 * @program: zyfboot-javabasic
 * @description: 猜数字大小
 * @author: zhangyanfeng
 * @create: 2024-08-24 12:26
 **/
public class GuessNumber {
    public int guessNumber(int n) {
        int left = 1;  // 初始搜索区间的左端点
        int right = n; // 初始搜索区间的右端点

        // 二分查找直到左端点和右端点相同
        while (left < right) {
            // 计算中点，避免溢出
            int mid = left + (right - left) / 2;
            // 调用 guess 方法获取中点的猜测结果
            int result = guess(mid);

            if (result == 0) {
                // 猜对了，返回中点
                return mid;
            } else if (result == -1) {
                // 目标数字小于 mid，缩小右边界
                right = mid;
            } else {
                // 目标数字大于 mid，缩小左边界
                left = mid + 1;
            }
        }

        // 当左端点和右端点相等时，即找到目标数字
        return left;
    }

    // 猜测接口方法，实际实现由平台提供
    private int guess(int num) {
        // 具体实现由平台提供
        return 0; // placeholder
    }
}
