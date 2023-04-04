package org.zyf.javabasic.letcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 题目描述：给出一个区间的集合，请合并所有重叠的区间。
 * 示例 1:输入: [[1,3],[2,6],[8,10],[15,18]]       输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:输入: [[1,4],[4,5]]       输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
 * @date 2023/4/2  01:04
 */
public class MergeIntervals {

    /**
     * 首先，我们将区间按照左端点从小到大进行排序，然后从第二个区间开始，依次判断是否与前一个区间有重合部分，
     * 如果有，则将两个区间合并成一个新的区间，并继续判断下一个区间是否能够与新区间合并。
     * 如果没有重合部分，则将前一个区间加入结果集中，重复以上过程，直到所有区间都被遍历完毕。
     * <p>
     * 这种解法的时间复杂度为 O(nlogn)，其中 n 表示区间的个数。
     * 因为排序的时间复杂度为 O(nlogn)，而遍历所有区间的时间复杂度也为 O(n)，所以总的时间复杂度为 O(nlogn)。
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }

        /*按照每个区间的左端点排序*/
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        /*存放最终合并后的区间*/
        List<int[]> merged = new ArrayList<>();

        /*遍历每个区间，判断是否需要合并*/
        for (int i = 0; i < intervals.length; i++) {
            int[] curr = intervals[i];
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < curr[0]) {
                /* 如果 merged 为空或者 merged 中最后一个区间的右端点小于 curr 区间的左端点，
                   则表示当前 curr 区间不能和前面的区间合并，直接将 curr 区间加入 merged 中*/
                merged.add(curr);
            } else {
                /*否则，说明需要将 curr 区间合并到前面的区间中*/
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], curr[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result = new MergeIntervals().merge(intervals);
        for (int[] interval : result) {
            System.out.println(Arrays.toString(interval));
        }
    }
}
