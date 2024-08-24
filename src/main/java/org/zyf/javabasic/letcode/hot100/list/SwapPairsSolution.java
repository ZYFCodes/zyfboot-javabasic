package org.zyf.javabasic.letcode.hot100.list;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @program: zyfboot-javabasic
 * @description: 两两交换链表中的节点（中等）  ​
 * @author: zhangyanfeng
 * @create: 2024-08-22 10:07
 **/
public class SwapPairsSolution {
    public ListNode swapPairs(ListNode head) {
        // 创建一个虚拟头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 初始化指针
        ListNode prev = dummy;

        // 迭代地交换相邻节点
        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = first.next;

            // 调整指针以交换节点
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // 移动 prev 指针到下一个要处理的节点对
            prev = first;
        }

        // 返回新链表的头节点
        return dummy.next;
    }

    public static void main(String[] args) {
        SwapPairsSolution solution = new SwapPairsSolution();

        // 测试用例 1
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        ListNode result1 = solution.swapPairs(l1);
        printList(result1); // 输出应该为 [2, 1, 4, 3]

        // 测试用例 2
        ListNode l2 = new ListNode(1);
        ListNode result2 = solution.swapPairs(l2);
        printList(result2); // 输出应该为 [1]

        // 测试用例 3
        ListNode result3 = solution.swapPairs(null);
        printList(result3); // 输出应该为 []
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
