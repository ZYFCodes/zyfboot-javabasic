package org.zyf.javabasic.letcode.hot100.list;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @program: zyfboot-javabasic
 * @description: 反转链表​
 * @author: zhangyanfeng
 * @create: 2024-08-21 23:54
 **/
public class ReverseListSolution {
    public ListNode reverseList1(ListNode head) {
        ListNode prev = null; // 前一个节点
        ListNode curr = head; // 当前节点

        while (curr != null) {
            ListNode next = curr.next; // 保存下一个节点
            curr.next = prev; // 反转当前节点的指针
            prev = curr; // 更新前一个节点
            curr = next; // 移动到下一个节点
        }

        return prev; // 返回新头节点
    }

    public ListNode reverseList2(ListNode head) {
        // 递归基准条件：链表为空或只有一个节点
        if (head == null || head.next == null) {
            return head;
        }

        // 递归反转链表的剩余部分
        ListNode newHead = reverseList2(head.next);

        // 反转当前节点和下一个节点
        head.next.next = head;
        head.next = null;

        return newHead; // 返回新的头节点
    }

    public static void main(String[] args) {
        ReverseListSolution solution = new ReverseListSolution();

        // 测试用例 1: 普通链表
        System.out.println("Test Case 1: [1, 2, 3, 4, 5]");
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        ListNode result1 = solution.reverseList1(head1);
        printList(result1);

        // 测试用例 2: 两个节点的链表
        System.out.println("Test Case 2: [1, 2]");
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        ListNode result2 = solution.reverseList1(head2);
        printList(result2);

        // 测试用例 3: 空链表
        System.out.println("Test Case 3: []");
        ListNode head3 = null;
        ListNode result3 = solution.reverseList2(head3);
        printList(result3);

        // 测试用例 4: 单节点链表
        System.out.println("Test Case 4: [1]");
        ListNode head4 = new ListNode(1);
        ListNode result4 = solution.reverseList2(head4);
        printList(result4);
    }

    // 打印链表的方法
    public static void printList(ListNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
