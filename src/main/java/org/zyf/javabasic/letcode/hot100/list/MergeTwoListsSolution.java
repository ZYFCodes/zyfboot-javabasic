package org.zyf.javabasic.letcode.hot100.list;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @program: zyfboot-javabasic
 * @description: 合并两个有序链表​
 * @author: zhangyanfeng
 * @create: 2024-08-22 09:49
 **/
public class MergeTwoListsSolution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 哨兵节点，方便返回结果
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // 遍历两个链表
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // 处理剩余的节点
        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        MergeTwoListsSolution solution = new MergeTwoListsSolution();

        // 测试用例 1
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode mergedList = solution.mergeTwoLists(l1, l2);
        printList(mergedList); // 应该输出 [1, 1, 2, 3, 4, 4]

        // 测试用例 2
        ListNode l3 = null;
        ListNode l4 = null;
        ListNode mergedList2 = solution.mergeTwoLists(l3, l4);
        printList(mergedList2); // 应该输出 []

        // 测试用例 3
        ListNode l5 = null;
        ListNode l6 = new ListNode(0);
        ListNode mergedList3 = solution.mergeTwoLists(l5, l6);
        printList(mergedList3); // 应该输出 [0]
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
