package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * 示例 1：输入：head = [1,2,3,4,5], k = 2     输出：[4,5,1,2,3]
 * 示例 2：输入：head = [0,1,2], k = 4         输出：[2,0,1]
 * <p>
 * 提示：
 * 链表中节点的数目在范围 [0, 5000] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 5000
 * @date 2023/4/5  23:52
 */
public class RotateList {

    /**
     * 旋转链表的最优解法的时间复杂度是O(n)，其中n是链表的长度。
     * 首先，需要找到倒数第k个节点，然后将其作为新的头结点。
     * 因为k有可能大于链表长度，所以需要先统计链表长度，并对k做一些处理。
     * <p>
     * 具体的做法如下：
     * 先遍历一遍链表，找到链表的长度，并把链表的尾部节点指向链表的头结点，形成一个环形链表。同时可以记录下原始的链表长度len。
     * 然后，从头节点开始，往后移动len - k % len个节点，找到新的头结点。
     * 以新的头结点为界，将原始的链表断开，形成新的链表，并返回新链表的头结点。
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        /*统计链表长度*/
        int len = 1;
        ListNode tail = head;
        while (tail.next != null) {
            len++;
            tail = tail.next;
        }

        /*计算需要移动的步数*/
        int step = len - k % len;

        /*链表变为环形，连接表头和表尾*/
        tail.next = head;

        /*寻找新的表头*/
        for (int i = 0; i < step; i++) {
            tail = head;
            head = head.next;
        }

        /*断开环形链表，返回新的表头*/
        tail.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int k = 2;

        ListNode newHead = new RotateList().rotateRight(head, k);

        while (newHead != null) {
            System.out.print(newHead.val + " ");
            newHead = newHead.next;
        }
    }
}
