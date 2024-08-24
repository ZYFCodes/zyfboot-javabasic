package org.zyf.javabasic.letcode.featured75.list;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @program: zyfboot-javabasic
 * @description: 删除链表的中间节点
 * @author: zhangyanfeng
 * @create: 2024-08-24 09:35
 **/
public class RemoveMiddleNode {
    public ListNode deleteMiddle(ListNode head) {
        // 如果链表只有一个节点，直接返回 null
        if (head == null || head.next == null) {
            return null;
        }

        // 初始化快指针和慢指针
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null; // 用于记录慢指针的前一个节点

        // 快指针移动两步，慢指针移动一步
        while (fast != null && fast.next != null) {
            prev = slow;       // 记录慢指针的前一个节点
            slow = slow.next;  // 慢指针移动一步
            fast = fast.next.next; // 快指针移动两步
        }

        // 此时 slow 指向中间节点，将其删除
        if (prev != null) {
            prev.next = slow.next; // 跳过中间节点
        }

        return head; // 返回删除中间节点后的链表头节点
    }

    public static void main(String[] args) {
        RemoveMiddleNode solution = new RemoveMiddleNode();

        // 测试用例1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(3);
        head1.next.next = new ListNode(4);
        head1.next.next.next = new ListNode(7);
        head1.next.next.next.next = new ListNode(1);
        head1.next.next.next.next.next = new ListNode(2);
        head1.next.next.next.next.next.next = new ListNode(6);

        ListNode newHead1 = solution.deleteMiddle(head1);
        printList(newHead1);  // 输出: [1, 3, 4, 1, 2, 6]

        // 测试用例2
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);

        ListNode newHead2 = solution.deleteMiddle(head2);
        printList(newHead2);  // 输出: [1, 2, 4]

        // 测试用例3
        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(1);

        ListNode newHead3 = solution.deleteMiddle(head3);
        printList(newHead3);  // 输出: [2]
    }

    // 辅助函数：打印链表
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
