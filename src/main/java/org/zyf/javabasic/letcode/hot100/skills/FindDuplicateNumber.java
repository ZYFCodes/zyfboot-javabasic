package org.zyf.javabasic.letcode.hot100.skills;

/**
 * @program: zyfboot-javabasic
 * @description: 寻找重复数（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 21:25
 **/
public class FindDuplicateNumber {
    public int findDuplicate(int[] nums) {
        // 阶段 1: 找到环的相遇点
        int slow = nums[0];
        int fast = nums[0];

        // 快慢指针移动，寻找相遇点
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // 阶段 2: 找到环的入口
        int finder = nums[0];
        while (finder != slow) {
            finder = nums[finder];
            slow = nums[slow];
        }

        return finder;
    }

    public static void main(String[] args) {
        FindDuplicateNumber fd = new FindDuplicateNumber();

        // 测试用例1
        int[] nums1 = {1, 3, 4, 2, 2};
        System.out.println("测试用例1结果: " + fd.findDuplicate(nums1)); // 输出: 2

        // 测试用例2
        int[] nums2 = {3, 1, 3, 4, 2};
        System.out.println("测试用例2结果: " + fd.findDuplicate(nums2)); // 输出: 3

        // 测试用例3
        int[] nums3 = {3, 3, 3, 3, 3};
        System.out.println("测试用例3结果: " + fd.findDuplicate(nums3)); // 输出: 3
    }
}
