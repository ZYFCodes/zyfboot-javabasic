package org.zyf.javabasic.letcode.hot100.skills;

/**
 * @program: zyfboot-javabasic
 * @description: 只出现一次的数字（简单）
 * @author: zhangyanfeng
 * @create: 2024-08-22 21:05
 **/
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num; // 对每个数字进行异或
        }
        return result; // 返回只出现一次的元素
    }

    public static void main(String[] args) {
        SingleNumber sn = new SingleNumber();

        // 测试用例1
        int[] nums1 = {2, 2, 1};
        System.out.println("测试用例1结果: " + sn.singleNumber(nums1)); // 输出：1

        // 测试用例2
        int[] nums2 = {4, 1, 2, 1, 2};
        System.out.println("测试用例2结果: " + sn.singleNumber(nums2)); // 输出：4

        // 测试用例3
        int[] nums3 = {1};
        System.out.println("测试用例3结果: " + sn.singleNumber(nums3)); // 输出：1
    }
}
