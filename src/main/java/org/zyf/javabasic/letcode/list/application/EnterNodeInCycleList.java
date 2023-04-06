package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description ​
 * 链表中环的入口节点是指一个有环链表中，环的入口节点。给定一个链表，若其中包含环，则输出环的入口节点；否则输出null。
 * 说明：
 * 不允许修改给定的链表。
 * 要求空间复杂度为O(1)。
 * @date 2023/4/5  23:45
 */
public class EnterNodeInCycleList {

    /**
     * 使用快慢指针法判断链表是否有环，
     * 如果有，快指针回到链表头部，然后快慢指针以相同的速度向前移动，
     * 当两个指针再次相遇时，该节点就是环的入口节点。
     * <p>
     * 该算法的时间复杂度为O(n)，空间复杂度为O(1)。
     */
    public ListNode entryNodeOfLoop(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        /*1. 判断是否有环*/
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            /*说明没有环*/
            return null;
        }

        /*2. 有环，找入口节点*/
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = head.next.next;

        ListNode entryNode = new EnterNodeInCycleList().entryNodeOfLoop(head);
        System.out.println(entryNode.val);
    }
}
