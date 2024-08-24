package org.zyf.javabasic.letcode.hot100.list;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @program: zyfboot-javabasic
 * @description: K 个一组翻转链表困难）
 * @author: zhangyanfeng
 * @create: 2024-08-22 10:14
 **/
public class ReverseKGroupSolution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        // 创建虚拟头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;

        while (true) {
            ListNode groupStart = prevGroupEnd.next;
            ListNode groupEnd = prevGroupEnd;

            // 找到当前组的最后一个节点
            for (int i = 0; i < k; i++) {
                groupEnd = groupEnd.next;
                if (groupEnd == null) {
                    return dummy.next; // 如果节点数不足 k，返回结果
                }
            }

            // 记录下一组的起始节点
            ListNode nextGroupStart = groupEnd.next;

            // 翻转当前组
            reverse(groupStart, groupEnd);

            // 连接前一组和当前组
            prevGroupEnd.next = groupEnd;
            groupStart.next = nextGroupStart;

            // 更新 prevGroupEnd 为当前组的最后一个节点（翻转后即 groupStart）
            prevGroupEnd = groupStart;
        }
    }

    // 翻转链表部分区域
    private void reverse(ListNode start, ListNode end) {
        ListNode prev = null;
        ListNode curr = start;
        ListNode next = null;

        while (prev != end) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
    }

    public static void main(String[] args) {
        ReverseKGroupSolution solution = new ReverseKGroupSolution();

        // 测试用例 1
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
        ListNode result1 = solution.reverseKGroup(l1, 2);
        printList(result1); // 输出应该为 [2, 1, 4, 3, 5]

        // 测试用例 2
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(3);
        l2.next.next.next = new ListNode(4);
        l2.next.next.next.next = new ListNode(5);
        ListNode result2 = solution.reverseKGroup(l2, 3);
        printList(result2); // 输出应该为 [3, 2, 1, 4, 5]
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
