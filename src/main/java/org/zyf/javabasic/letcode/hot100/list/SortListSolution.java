package org.zyf.javabasic.letcode.hot100.list;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @program: zyfboot-javabasic
 * @description: 排序链表（中等）  ​
 * @author: zhangyanfeng
 * @create: 2024-08-22 10:26
 **/
public class SortListSolution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Step 1: 找到链表的中间节点，并将链表分成两部分
        ListNode mid = getMid(head);
        ListNode left = head;
        ListNode right = mid.next;
        mid.next = null;

        // Step 2: 递归地对两部分排序
        left = sortList(left);
        right = sortList(right);

        // Step 3: 合并两部分
        return merge(left, right);
    }

    // 使用快慢指针找链表的中间节点
    private ListNode getMid(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 合并两个有序链表
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    public static void main(String[] args) {
        SortListSolution solution = new SortListSolution();

        // 测试用例 1
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        ListNode sortedList1 = solution.sortList(node1);
        printList(sortedList1); // 输出：[1, 2, 3, 4]

        // 测试用例 2
        ListNode node5 = new ListNode(-1);
        ListNode node6 = new ListNode(5);
        ListNode node7 = new ListNode(3);
        ListNode node8 = new ListNode(4);
        ListNode node9 = new ListNode(0);
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        ListNode sortedList2 = solution.sortList(node5);
        printList(sortedList2); // 输出：[-1, 0, 3, 4, 5]

        // 测试用例 3
        ListNode sortedList3 = solution.sortList(null);
        printList(sortedList3); // 输出：[]
    }

    // 打印链表
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
