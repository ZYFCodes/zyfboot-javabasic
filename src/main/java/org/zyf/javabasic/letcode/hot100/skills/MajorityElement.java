package org.zyf.javabasic.letcode.hot100.skills;

/**
 * @program: zyfboot-javabasic
 * @description: 多数元素（简单）
 * @author: zhangyanfeng
 * @create: 2024-08-22 21:09
 **/
public class MajorityElement {
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }

    public static void main(String[] args) {
        MajorityElement me = new MajorityElement();

        // 测试用例1
        int[] nums1 = {3, 2, 3};
        System.out.println("测试用例1结果: " + me.majorityElement(nums1)); // 输出：3

        // 测试用例2
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println("测试用例2结果: " + me.majorityElement(nums2)); // 输出：2
    }
}
