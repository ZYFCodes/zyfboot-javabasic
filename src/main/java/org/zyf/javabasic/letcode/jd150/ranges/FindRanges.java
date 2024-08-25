package org.zyf.javabasic.letcode.jd150.ranges;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 汇总区间
 * @author: zhangyanfeng
 * @create: 2024-08-25 12:21
 **/
public class FindRanges {
    public List<String> findRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums.length == 0) {
            return result; // 如果数组为空，返回空列表
        }

        int start = nums[0]; // 区间起始值
        int end = nums[0];   // 区间结束值

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == end + 1) {
                // 如果当前元素与结束值连续，扩展当前区间
                end = nums[i];
            } else {
                // 否则，结束当前区间，添加到结果中，并开始新的区间
                result.add(formatRange(start, end));
                start = nums[i];
                end = nums[i];
            }
        }

        // 处理最后一个区间
        result.add(formatRange(start, end));
        return result;
    }

    private String formatRange(int start, int end) {
        // 格式化区间为字符串
        if (start == end) {
            return String.valueOf(start);
        } else {
            return start + "->" + end;
        }
    }

    public static void main(String[] args) {
        FindRanges sol = new FindRanges();
        int[] nums1 = {0, 1, 2, 4, 5, 7};
        int[] nums2 = {0, 2, 3, 4, 6, 8, 9};

        System.out.println(sol.findRanges(nums1)); // 输出: ["0->2","4->5","7"]
        System.out.println(sol.findRanges(nums2)); // 输出: ["0","2->4","6","8->9"]
    }
}
