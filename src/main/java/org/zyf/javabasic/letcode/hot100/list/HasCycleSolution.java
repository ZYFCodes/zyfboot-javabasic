package org.zyf.javabasic.letcode.hot100.list;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @program: zyfboot-javabasic
 * @description: 环形链表​
 * @author: zhangyanfeng
 * @create: 2024-08-22 00:12
 **/
public class HasCycleSolution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false; // 空链表或只有一个节点的链表不可能有环
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next; // 慢指针每次移动一个节点
            fast = fast.next.next; // 快指针每次移动两个节点

            if (slow == fast) { // 快慢指针相遇，说明链表有环
                return true;
            }
        }

        return false; // 快指针到达链表末尾，说明链表没有环
    }

    public static void main(String[] args) {
        HasCycleSolution solution = new HasCycleSolution();

        // 测试用例 1: 有环的链表
        ListNode head1 = new ListNode(3);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(0);
        head1.next.next.next = new ListNode(-4);
        head1.next.next.next.next = head1.next; // 尾部连接到第二个节点
        System.out.println("Test Case 1: " + solution.hasCycle(head1)); // 应该输出 true

        // 测试用例 2: 有环的链表
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = head2; // 尾部连接到第一个节点
        System.out.println("Test Case 2: " + solution.hasCycle(head2)); // 应该输出 true

        // 测试用例 3: 无环的链表
        ListNode head3 = new ListNode(1);
        System.out.println("Test Case 3: " + solution.hasCycle(head3)); // 应该输出 false
    }
}
