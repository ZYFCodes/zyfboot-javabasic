package org.zyf.javabasic.letcode.jzoffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 输入一个正整数 target，输出所有和为 target 的连续正数序列（至少含有两个数）。
 * 序列内的数字要求按从小到大的顺序排列。
 * @date 2023/6/30  23:07
 */
public class FindContinuousSequence {
    /**
     * 可以使用穷举的方法来解决该问题。设定两个指针 start 和 end，分别表示连续序列的起始值和结束值。初始时，start 和 end 均为 1。根据连续序列的性质，可以进行如下操作：
     * •	如果从 start 到 end 的序列的和等于 target，将这个序列添加到结果列表中，并将 end 向后移动一位，继续寻找下一个序列。
     * •	如果从 start 到 end 的序列的和小于 target，将 end 向后移动一位，扩大序列范围，使得序列和增大。
     * •	如果从 start 到 end 的序列的和大于 target，将 start 向后移动一位，缩小序列范围，使得序列和减小。
     * •	当 start 大于等于 target 的一半时，即可停止寻找，因为在这之后的序列和一定大于 target。
     */
    public List<List<Integer>> findContinuousSequence(int target) {
        List<List<Integer>> result = new ArrayList<>();
        int start = 1;
        int end = 1;
        int sum = 0;

        while (start <= target / 2) {
            if (sum < target) {
                sum += end;
                end++;
            } else if (sum > target) {
                sum -= start;
                start++;
            } else {
                List<Integer> sequence = new ArrayList<>();
                for (int i = start; i < end; i++) {
                    sequence.add(i);
                }
                result.add(sequence);
                sum -= start;
                start++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        FindContinuousSequence solution = new FindContinuousSequence();

        int target = 9;
        List<List<Integer>> result = solution.findContinuousSequence(target);

        System.out.println("和为 " + target + " 的连续正数序列：");
        for (List<Integer> sequence : result) {
            System.out.println(sequence);
        }
    }

}
