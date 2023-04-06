package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给定一个头结点为 head 的非空单链表，返回链表的中间结点。如果有两个中间结点，则返回第二个中间结点。
 * 例如，给定链表 1->2->3->4->5->NULL，返回结点 3；给定链表 1->2->3->4->5->6->NULL，返回结点 4。
 * <p>
 * 提示：给定链表的结点数介于 1 和 100 之间。
 * @date 2023/4/5  23:14
 */
public class MiddleNode {

    /**
     * 最优的解法是快慢指针法。
     * <p>
     * 使用两个指针，一个快指针和一个慢指针，
     * 快指针每次移动两个节点，慢指针每次移动一个节点，
     * 当快指针到达链表末尾时，慢指针就指向链表的中间节点。
     * <p>
     * 这个算法的时间复杂度为O(n)，其中n为链表的长度，空间复杂度为O(1)，只需要两个指针的空间。
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        /*快指针每次走两步，
        慢指针每次走一步，当快指针走到链表末尾时，慢指针刚好在中间节点。*/
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode middle = new MiddleNode().middleNode(head);
        System.out.println(middle.val);
    }


}
