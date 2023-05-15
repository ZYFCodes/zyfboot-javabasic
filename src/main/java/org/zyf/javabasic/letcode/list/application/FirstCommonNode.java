package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给定两个单链表，判断两个链表是否相交。
 * 若相交，返回相交的起始节点。若不相交，返回 null。可以假定整个链表结构中没有循环。
 * 注意，函数返回结果后，链表必须保持其原始结构。
 * 提示：
 * 如果两个链表相交，它们的最后一个节点一定是共同的。
 * 由于单链表的节点只有一个 next 指针，所以每次只能遍历一个节点。
 * @date 2023/4/5  23:06
 */
public class FirstCommonNode {

    /**
     * 求两个单链表的交点===链表相交问题可以采用双指针法来解决，具体步骤如下：
     * 首先分别遍历两个链表，得到它们的长度，以及它们的尾节点；
     * 如果两个链表的尾节点不同，说明它们不相交，直接返回null；
     * 然后再分别从两个链表的头节点开始，让长链表的指针先走 abs(len1-len2) 步，这样两个链表的指针就在同一起跑线上了；
     * 接下来，同时遍历两个链表，比较它们每个节点是否相同，直到找到相交节点，或者到达链表的尾部。
     * 时间复杂度为 O(m+n)，其中m和n分别为两个链表的长度。
     *
     * @param headA 单链表A的头节点
     * @param headB 单链表B的头节点
     * @return 两个单链表的交点，若不存在交点则返回 null
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = getLength(headA);
        int lenB = getLength(headB);
        /*将较长的链表头节点指针移动到与另一个链表相同位置*/
        if (lenA > lenB) {
            headA = moveHead(headA, lenA - lenB);
        } else {
            headB = moveHead(headB, lenB - lenA);
        }
        /*同时遍历两个链表，找到第一个相同的节点*/
        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        /*两个链表没有相交的节点*/
        return null;
    }

    /**
     * 计算链表的长度
     */
    private int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    /**
     * 将链表头节点指针移动 n 步
     */
    private ListNode moveHead(ListNode head, int n) {
        while (n > 0) {
            head = head.next;
            n--;
        }
        return head;
    }

    public static void main(String[] args) {
        /*创建两个链表：1->2->3->4->5 和 6->7->4->5*/
        ListNode commonNode = new ListNode(4);
        commonNode.next = new ListNode(5);
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(3);
        headA.next.next.next = commonNode;
        ListNode headB = new ListNode(6);
        headB.next = new ListNode(7);
        headB.next.next = commonNode;
        /*找到两个链表的第一个公共节点*/
        ListNode intersectionNode = new FirstCommonNode().getIntersectionNode(headA, headB);
        /*输出第一个公共节点的值*/
        if (intersectionNode != null) {
            /*4*/
            System.out.println(intersectionNode.val);
        } else {
            System.out.println("null");
        }
    }
}
