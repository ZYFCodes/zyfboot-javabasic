package org.zyf.javabasic.letcode.jzoffer;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 从扑克牌中随机抽取 5 张牌，判断是不是一个顺子，即这 5 张牌是否连续。
 * 其中 A 表示 1，J 表示 11，Q 表示 12，K 表示 13，而大、小王可以看成任意数字。
 * @date 2023/6/8  22:36
 */
public class StraightPoker {
    /**
     * 要判断一手牌是否为顺子，需要满足以下条件：
     * 1.	除了大小王以外，牌中没有重复的数字；
     * 2.	所有牌中的最大值减去最小值小于等于 4。
     * 具体步骤如下：
     * 1.	对数组进行排序，将大小王放在最后，以便判断最大值和最小值；
     * 2.	统计大小王的个数，记为 count；
     * 3.	统计排序后的数组中相邻数字之间的间隔数目，记为 gap；
     * 4.	如果 gap 小于等于 count，说明可以通过使用大小王填补间隔，构成连续的顺子；
     * 5.	如果 gap 大于 count，说明无法通过大小王填补间隔，不能构成连续的顺子。
     */
    public boolean isStraight(int[] nums) {
        if (nums == null || nums.length != 5) {
            return false;
        }

        // 对数组进行排序
        Arrays.sort(nums);

        // 大小王的个数
        int count = 0;
        // 相邻数字之间的间隔数目
        int gap = 0;

        // 统计大小王的个数
        for (int num : nums) {
            if (num == 0) {
                count++;
            }
        }

        // 统计相邻数字之间的间隔数目
        for (int i = count; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                // 存在重复数字，不是顺子
                return false;
            }
            gap += nums[i + 1] - nums[i] - 1;
        }

        // 判断是否可以通过大小王填补间隔
        return gap <= count;
    }

    public static void main(String[] args) {
        StraightPoker solution = new StraightPoker();
        int[] nums1 = {1, 2, 3, 4, 5};
        int[] nums2 = {0, 0, 1, 2, 5};
        // true
        System.out.println(solution.isStraight(nums1));
        // true
        System.out.println(solution.isStraight(nums2));
    }

}
