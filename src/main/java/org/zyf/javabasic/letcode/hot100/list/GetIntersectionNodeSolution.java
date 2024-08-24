package org.zyf.javabasic.letcode.hot100.list;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @program: zyfboot-javabasic
 * @description: 相交链表​
 * @author: zhangyanfeng
 * @create: 2024-08-21 23:43
 **/
public class GetIntersectionNodeSolution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 如果任何一个链表为空，直接返回 null
        if (headA == null || headB == null) {
            return null;
        }

        // 初始化两个指针
        ListNode pA = headA;
        ListNode pB = headB;

        // 当两个指针不相等时继续遍历
        while (pA != pB) {
            // 遇到链表末尾时，切换到另一链表
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }

        // 返回相交的节点或者 null
        return pA;
    }

    public static void main(String[] args) {
        GetIntersectionNodeSolution solution = new GetIntersectionNodeSolution();

        // 创建链表节点
        ListNode common = new ListNode(8);
        common.next = new ListNode(4);
        common.next.next = new ListNode(5);

        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = common;

        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = common;

        // Test Case 1: 链表有交点
        ListNode result1 = solution.getIntersectionNode(headA, headB);
        System.out.println(result1 != null ? result1.val : "null"); // Expected: 8

        // Test Case 2: 链表没有交点
        ListNode headC = new ListNode(2);
        headC.next = new ListNode(6);
        headC.next.next = new ListNode(4);

        ListNode headD = new ListNode(1);
        headD.next = new ListNode(5);

        ListNode result2 = solution.getIntersectionNode(headC, headD);
        System.out.println(result2 != null ? result2.val : "null"); // Expected: null
    }
}
