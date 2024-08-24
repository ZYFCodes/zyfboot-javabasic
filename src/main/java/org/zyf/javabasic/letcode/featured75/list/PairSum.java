package org.zyf.javabasic.letcode.featured75.list;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @program: zyfboot-javabasic
 * @description: 链表最大孪生和
 * @author: zhangyanfeng
 * @create: 2024-08-24 09:56
 **/
public class PairSum {
    public int pairSum(ListNode head) {
        // 快慢指针找到链表的中点
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 反转链表的后半部分
        ListNode prev = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        // 计算孪生和的最大值
        int maxSum = 0;
        ListNode start = head;
        while (prev != null) {
            maxSum = Math.max(maxSum, start.val + prev.val);
            start = start.next;
            prev = prev.next;
        }

        return maxSum;
    }

    public static void main(String[] args) {
        // 示例1
        ListNode head1 = new ListNode(5);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(2);
        head1.next.next.next = new ListNode(1);
        PairSum solution = new PairSum();
        System.out.println(solution.pairSum(head1)); // 输出应为 6

        // 示例2
        ListNode head2 = new ListNode(4);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(2);
        head2.next.next.next = new ListNode(3);
        System.out.println(solution.pairSum(head2)); // 输出应为 7

        // 示例3
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(100000);
        System.out.println(solution.pairSum(head3)); // 输出应为 100001
    }
}
