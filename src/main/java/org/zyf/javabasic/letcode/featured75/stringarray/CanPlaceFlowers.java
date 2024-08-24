package org.zyf.javabasic.letcode.featured75.stringarray;

/**
 * @program: zyfboot-javabasic
 * @description: 种花问题
 * @author: zhangyanfeng
 * @create: 2024-08-23 22:33
 **/
public class CanPlaceFlowers {
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int length = flowerbed.length;

        for (int i = 0; i < length && n > 0; i++) {
            // 如果当前位置是0，并且前后（考虑边界）也是0，说明可以种花
            if (flowerbed[i] == 0) {
                // 检查左边（i == 0 时，左边没有地块）
                boolean leftEmpty = (i == 0) || (flowerbed[i - 1] == 0);
                // 检查右边（i == length - 1 时，右边没有地块）
                boolean rightEmpty = (i == length - 1) || (flowerbed[i + 1] == 0);

                if (leftEmpty && rightEmpty) {
                    // 种花
                    flowerbed[i] = 1;
                    n--;
                }
            }
        }

        return n <= 0;
    }

    // 测试方法
    public static void main(String[] args) {
        int[] flowerbed1 = {1, 0, 0, 0, 1};
        int n1 = 1;
        System.out.println(canPlaceFlowers(flowerbed1, n1)); // 输出: true

        int[] flowerbed2 = {1, 0, 0, 0, 1};
        int n2 = 2;
        System.out.println(canPlaceFlowers(flowerbed2, n2)); // 输出: false
    }
}
