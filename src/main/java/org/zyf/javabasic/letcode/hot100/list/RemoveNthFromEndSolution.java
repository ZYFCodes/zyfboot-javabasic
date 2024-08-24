package org.zyf.javabasic.letcode.hot100.list;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @program: zyfboot-javabasic
 * @description: 删除链表的倒数第 N 个结点（中等）  ​
 * @author: zhangyanfeng
 * @create: 2024-08-22 10:01
 **/
public class RemoveNthFromEndSolution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 创建一个哨兵节点，以应对删除头节点的情况
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        // 快指针先移动 n 步
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // 快慢指针一起移动，直到快指针到达链表末尾
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 删除慢指针所指节点的下一个节点
        slow.next = slow.next.next;

        // 返回链表头节点
        return dummy.next;
    }

    public static void main(String[] args) {
        RemoveNthFromEndSolution solution = new RemoveNthFromEndSolution();

        // 测试用例 1
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
        ListNode result1 = solution.removeNthFromEnd(l1, 2);
        printList(result1); // 输出应该为 [1, 2, 3, 5]

        // 测试用例 2
        ListNode l2 = new ListNode(1);
        ListNode result2 = solution.removeNthFromEnd(l2, 1);
        printList(result2); // 输出应该为 []

        // 测试用例 3
        ListNode l3 = new ListNode(1);
        l3.next = new ListNode(2);
        ListNode result3 = solution.removeNthFromEnd(l3, 1);
        printList(result3); // 输出应该为 [1]
    }

    // 打印链表的辅助函数
    private static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }
}
