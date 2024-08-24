package org.zyf.javabasic.letcode.hot100.list;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @program: zyfboot-javabasic
 * @description: 环形链表 II​
 * @author: zhangyanfeng
 * @create: 2024-08-22 00:17
 **/
public class DetectCycleSolution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null; // 空链表或只有一个节点的链表没有环
        }

        ListNode slow = head;
        ListNode fast = head;

        // 阶段 1: 检测是否有环
        while (fast != null && fast.next != null) {
            slow = slow.next; // 慢指针每次移动一个节点
            fast = fast.next.next; // 快指针每次移动两个节点

            if (slow == fast) { // 快慢指针相遇，说明链表有环
                // 阶段 2: 找到环的起始节点
                ListNode entry = head; // 从头节点开始
                while (entry != slow) { // entry 和 slow 都以相同速度移动
                    entry = entry.next;
                    slow = slow.next;
                }
                return entry; // entry 即为环的起始节点
            }
        }

        return null; // 无环
    }

    public static void main(String[] args) {
        DetectCycleSolution solution = new DetectCycleSolution();

        // 测试用例 1: 有环的链表
        ListNode head1 = new ListNode(3);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(0);
        head1.next.next.next = new ListNode(-4);
        head1.next.next.next.next = head1.next; // 尾部连接到第二个节点
        System.out.println("Test Case 1: " + (solution.detectCycle(head1) != null ? solution.detectCycle(head1).val : "null")); // 应该输出 2

        // 测试用例 2: 有环的链表
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = head2; // 尾部连接到第一个节点
        System.out.println("Test Case 2: " + (solution.detectCycle(head2) != null ? solution.detectCycle(head2).val : "null")); // 应该输出 1

        // 测试用例 3: 无环的链表
        ListNode head3 = new ListNode(1);
        System.out.println("Test Case 3: " + (solution.detectCycle(head3) != null ? solution.detectCycle(head3).val : "null")); // 应该输出 null
    }
}
