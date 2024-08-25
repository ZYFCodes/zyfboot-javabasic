package org.zyf.javabasic.letcode.jd150.stringarray;

/**
 * @program: zyfboot-javabasic
 * @description: 删除有序数组中的重复项
 * @author: zhangyanfeng
 * @create: 2024-08-24 20:27
 **/
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        // 如果数组为空，直接返回0
        if (nums.length == 0) {
            return 0;
        }

        // 初始化慢指针
        int slow = 0;

        // 快指针从1开始遍历
        for (int fast = 1; fast < nums.length; fast++) {
            // 如果当前元素和慢指针元素不同
            if (nums[fast] != nums[slow]) {
                // 慢指针前移
                slow++;
                // 将快指针的值赋给慢指针当前位置
                nums[slow] = nums[fast];
            }
        }

        // 返回不重复元素的数量
        return slow + 1;
    }
}
