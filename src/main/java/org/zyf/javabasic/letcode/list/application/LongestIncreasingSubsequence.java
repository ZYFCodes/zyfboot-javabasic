package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给定一个未排序的链表，找到其中最长的递增子序列的长度。
 * 例如，对于链表 1->3->5->4->7，最长的递增子序列是 1->3->4->7，长度为 4。
 * @date 2023/2/3  22:55
 */
public class LongestIncreasingSubsequence {

    /**
     * 最优解法使用动态规划结合二分查找，其时间复杂度为 O(nlogn)，其中 n 是链表的节点数。
     * 具体步骤如下：
     * * 定义一个数组 tails 来保存递增子序列，其中 tails[i] 表示长度为 i+1 的递增子序列的最后一个元素的值。
     * * 对于链表中的每个节点，进行二分查找找到它在 tails 数组中的插入位置。
     * * 如果当前节点的值大于 tails 数组中的最后一个元素，说明可以将当前节点添加到递增子序列中，长度加一。
     * * 否则，找到插入位置并更新 tails 数组中对应位置的值为当前节点的值，这样可以保持 tails 数组的递增性。
     * * 最终，tails 数组的长度即为最长的递增子序列的长度。
     * 使用动态规划结合二分查找的方法，每次查找操作的时间复杂度为 O(logn)，总共需要进行 n 次查找，因此总时间复杂度为 O(nlogn)。
     * 空间复杂度为 O(n)，需要一个额外的 tails 数组来保存中间结果。
     * 这种解法在寻找最长递增子序列的长度时非常高效，适用于规模较大的链表。
     */
    public static int lengthOfLIS(ListNode head) {
        if (head == null) {
            return 0;
        }

        // 定义一个数组 tails 来保存递增子序列，其中 tails[i] 表示长度为 i+1 的递增子序列的最后一个元素的值
        int[] tails = new int[headLength(head)];
        int len = 0;

        while (head != null) {
            // 进行二分查找找到当前节点在 tails 数组中的插入位置
            int left = 0;
            int right = len;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < head.val) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            // 如果当前节点的值大于 tails 数组中的最后一个元素，说明可以将当前节点添加到递增子序列中，长度加一
            if (left == len) {
                tails[len++] = head.val;
            } else {
                // 否则，找到插入位置并更新 tails 数组中对应位置的值为当前节点的值，这样可以保持 tails 数组的递增性
                tails[left] = head.val;
            }

            // 继续处理下一个节点
            head = head.next;
        }

        // 最终，tails 数组的长度即为最长的递增子序列的长度
        return len;
    }

    // 计算链表的长度
    private static int headLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    public static void main(String[] args) {
        // 构建一个测试链表：1->3->5->4->7
        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(5);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(7);

        // 调用求最长递增子序列长度的函数
        int result = lengthOfLIS(head);

        // 验证结果为4，因为最长递增子序列是 1->3->4->7
        System.out.println("Length of LIS: " + result);
    }

}
