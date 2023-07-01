package org.zyf.javabasic.letcode.jzoffer;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * @date 2023/7/1  19:48
 */
public class DeleteListNode {
    /**
     * 删除链表中的节点有以下几种情况：
     * 1.	要删除的节点是头节点：直接将头指针指向头节点的下一个节点即可。
     * 2.	要删除的节点不是头节点：
     * 遍历链表，找到要删除的节点的前一个节点，将前一个节点的指针指向要删除节点的下一个节点。
     */
    public ListNode deleteNode(ListNode head, int val) {
        // 处理头节点是要删除的节点的情况
        if (head != null && head.val == val) {
            return head.next;
        }

        ListNode cur = head;
        ListNode prev = null;

        // 遍历链表查找要删除的节点
        while (cur != null) {
            if (cur.val == val) {
                // 删除节点
                prev.next = cur.next;
                break;
            }
            prev = cur;
            cur = cur.next;
        }

        return head;
    }

    public static void main(String[] args) {
        DeleteListNode solution = new DeleteListNode();

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

        int val = 3;

        // 删除节点
        ListNode newHead = solution.deleteNode(head, val);

        // 打印链表
        ListNode cur = newHead;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }

}
