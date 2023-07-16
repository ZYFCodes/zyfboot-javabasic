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
    public static int findMajorityElement(int[] nums) {
        // 候选元素
        int candidate = 0;
        // 候选元素的出现次数
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                // 如果当前候选元素的出现次数为0，则将当前元素设为候选元素，并将出现次数设为1
                candidate = num;
                count = 1;
            } else if (num == candidate) {
                // 如果当前元素与候选元素相同，则候选元素的出现次数加1
                count++;
            } else {
                // 如果当前元素与候选元素不同，则候选元素的出现次数减1
                count--;
            }
        }

        // 最终候选元素可能是多数元素，需要再次验证
        int countCandidate = 0;
        for (int num : nums) {
            if (num == candidate) {
                countCandidate++;
            }
        }

        // -1 表示没有多数元素
        return countCandidate > nums.length / 2 ? candidate : -1;
    }


    public static void main(String[] args) {
        int[] nums = {3, 3, 3, 2, 5};
        int result = new MajorityElement().findMajorityElement(nums);
        /*输出 3*/
        System.out.println(result);
    }

}
