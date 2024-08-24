package org.zyf.javabasic.letcode.hot100.twopoint;

/**
 * @program: zyfboot-javabasic
 * @description: 盛最多水的容器  ​
 * @author: zhangyanfeng
 * @create: 2024-08-21 20:54
 **/
public class MaxAreaSolution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        // 使用双指针法计算最大面积
        while (left < right) {
            // 计算当前指针指向的垂线形成的容器的面积
            int currentArea = Math.min(height[left], height[right]) * (right - left);
            // 更新最大面积
            maxArea = Math.max(maxArea, currentArea);

            // 移动较小的一端的指针
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        MaxAreaSolution solution = new MaxAreaSolution();

        int[] height1 = {1,8,6,2,5,4,8,3,7};
        System.out.println(solution.maxArea(height1)); // 输出: 49

        int[] height2 = {1,1};
        System.out.println(solution.maxArea(height2)); // 输出: 1
    }
}
