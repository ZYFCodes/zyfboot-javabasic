package org.zyf.javabasic.letcode.jzoffer;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 输入一个链表，输出该链表中倒数第k个节点。
 * 假设该链表中节点数量大于等于k。
 * @date 2023/6/6  23:50
 */
public class KthFromEnd {
    /**
     * 要找到链表中倒数第k个节点，可以使用双指针法。定义两个指针fast和slow，初始时都指向链表的头节点。
     * 首先，让fast指针先向前移动k个位置，然后同时移动fast和slow指针，
     * 直到fast指针到达链表末尾。此时，slow指针指向的节点就是倒数第k个节点。
     * 具体步骤如下：
     * 	1.	初始化两个指针fast和slow，都指向链表的头节点。
     * 	2.	让fast指针先向前移动k个位置。
     * 	3.	同时移动fast和slow指针，直到fast指针到达链表末尾。
     * 	4.	返回slow指针指向的节点。
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;

        // 让fast指针先向前移动k个位置
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        // 同时移动fast和slow指针
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        KthFromEnd solution = new KthFromEnd();

        // 构建链表: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        int k = 2;

        // 获取倒数第k个节点
        ListNode kthNode = solution.getKthFromEnd(head, k);

        // 打印倒数第k个节点的值
        System.out.println(kthNode.val);
    }

}
