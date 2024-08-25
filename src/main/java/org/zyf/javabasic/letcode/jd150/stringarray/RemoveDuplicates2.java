package org.zyf.javabasic.letcode.jd150.stringarray;

/**
 * @program: zyfboot-javabasic
 * @description: RemoveDuplicates
 * @author: zhangyanfeng
 * @create: 2024-08-24 20:32
 **/
public class RemoveDuplicates2 {
    public int removeDuplicates(int[] nums) {
        // 如果数组长度小于等于2，则不需要处理，直接返回数组长度
        if (nums.length <= 2) {
            return nums.length;
        }

        // 初始化慢指针指向第二个元素
        int slow = 2;

        // 快指针从第三个元素开始遍历
        for (int fast = 2; fast < nums.length; fast++) {
            // 如果当前元素大于slow-2位置的元素，说明它可以被保留
            if (nums[fast] > nums[slow - 2]) {
                // 将fast指针的值赋给slow指针，并将slow指针向前移动
                nums[slow] = nums[fast];
                slow++;
            }
        }

        // 最终slow的位置就是数组的有效长度
        return slow;
    }
}
