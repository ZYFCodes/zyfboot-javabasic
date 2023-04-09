package org.zyf.javabasic.letcode.hash;

/**
 * @author yanfengzhang
 * @description 给定一个大小为 n 的数组，找到其中的多数元素。
 * 多数元素指在数组中出现次数大于 ⌊n/2⌋ 的元素。
 * @date 2023/4/9  19:21
 */
public class MajorityElement {

    /**
     * 摩尔投票法：该算法基于一个事实，即在一个数组中，若某个元素出现次数大于数组长度的一半，则该元素必定存在。
     * <p>
     * 算法步骤如下：
     * 1 设置一个候选众数 candidate 和一个计数器 count，初始值分别为任意值和 0。
     * 2 遍历数组 nums 中的每个元素，如果计数器 count 为 0，则将当前元素设置为候选众数 candidate，并将计数器 count 设置为 1；
     * 否则，如果当前元素等于候选众数 candidate，则将计数器 count 加 1，否则将计数器 count 减 1。
     * 3 遍历完数组后，candidate 即为众数。
     */
    public int majorityElement(int[] nums) {
        /*初始化候选人为第一个数*/
        int candidate = nums[0];
        /*初始化计数器为 0*/
        int count = 0;

        /*Boyer-Moore 投票算法*/
        for (int num : nums) {
            if (count == 0) {
                /*如果计数器为 0，则将当前数设为候选人*/
                candidate = num;
            }
            /*如果当前数等于候选人，则计数器加 1，否则计数器减 1*/
            count += (num == candidate) ? 1 : -1;
        }
        /*返回候选人*/
        return candidate;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3};
        int result = new MajorityElement().majorityElement(nums);
        /*输出 3*/
        System.out.println(result);
    }

}
