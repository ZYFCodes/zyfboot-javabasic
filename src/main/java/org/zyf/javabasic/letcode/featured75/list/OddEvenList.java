package org.zyf.javabasic.letcode.featured75.list;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @program: zyfboot-javabasic
 * @description: 奇偶链表
 * @author: zhangyanfeng
 * @create: 2024-08-24 09:40
 **/
public class OddEvenList {
    public ListNode oddEvenList(ListNode head) {
        // 如果链表为空或只有一个节点，直接返回
        if (head == null || head.next == null) {
            return head;
        }

        // 初始化奇数指针odd，偶数指针even，以及偶数链表头evenHead
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        // 遍历链表，重排节点
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        // 将奇数链表的末尾连接到偶数链表头部
        odd.next = evenHead;

        return head;
    }

    public static void main(String[] args) {
        // 测试用例 1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);

        OddEvenList solution = new OddEvenList();
        ListNode result1 = solution.oddEvenList(head1);
        printList(result1);  // 输出应为: 1 -> 3 -> 5 -> 2 -> 4

        // 测试用例 2
        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(1);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(5);
        head2.next.next.next.next = new ListNode(6);
        head2.next.next.next.next.next = new ListNode(4);
        head2.next.next.next.next.next.next = new ListNode(7);

        ListNode result2 = solution.oddEvenList(head2);
        printList(result2);  // 输出应为: 2 -> 3 -> 6 -> 7 -> 1 -> 5 -> 4
    }

    // 辅助函数，用于打印链表
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }
}
