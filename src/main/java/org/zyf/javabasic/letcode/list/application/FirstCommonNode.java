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
        if (headA == null || headB == null) {
            /*若其中一个链表为空，则直接返回 null*/
            return null;
        }
        /*定义指针 pA 和 pB 分别指向链表 A 和 B 的头节点*/
        ListNode pA = headA, pB = headB;
        /*若两个指针不相遇，则一直循环下去*/
        while (pA != pB) {
            /*当指针 pA 到达链表 A 的末尾时，将其指向链表 B 的头节点，继续遍历*/
            pA = pA == null ? headB : pA.next;
            /*当指针 pB 到达链表 B 的末尾时，将其指向链表 A 的头节点，继续遍历*/
            pB = pB == null ? headA : pB.next;
        }
        /*返回两个指针相遇的节点*/
        return pA;
    }

    public static void main(String[] args) {
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        ListNode intersection = new ListNode(3);
        headA.next.next = intersection;
        intersection.next = new ListNode(4);
        intersection.next.next = new ListNode(5);

        ListNode headB = new ListNode(6);
        headB.next = intersection;

        ListNode intersectionNode = new FirstCommonNode().getIntersectionNode(headA, headB);

        System.out.println(intersectionNode.val);
    }
}
