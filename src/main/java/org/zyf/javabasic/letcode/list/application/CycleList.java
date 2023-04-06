package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给定一个链表，判断链表中是否有环。
 * 进阶：你能否不使用额外空间解决此题？
 * @date 2023/4/5  23:27
 */
public class CycleList {
    /**
     * 判断链表中是否有环的高效最优解法是使用快慢指针，也称为龟兔赛跑算法。具体步骤如下：
     * 定义两个指针 slow 和 fast，初始值都指向链表头节点。
     * 每次将 slow 指针向后移动一步，将 fast 指针向后移动两步。
     * 如果链表中有环，则快指针一定会追上慢指针，此时可以返回 true。
     * 如果链表中没有环，则快指针会先到达链表末尾，此时可以返回 false。
     * <p>
     * 在这个算法中，时间复杂度是 O(n)，空间复杂度是 O(1)，
     * 因为只使用了两个指针来遍历链表，没有使用额外的数据结构来存储中间结果。
     */
    public boolean hasCycle(ListNode head) {
        /*判断链表为空或链表中只有一个节点的情况*/
        if (head == null || head.next == null) {
            return false;
        }
        /*定义快慢指针，初始值都指向链表头节点*/
        ListNode slow = head;
        ListNode fast = head.next;
        /*当快指针没有追上慢指针且链表中还有节点时，继续循环*/
        while (slow != fast) {
            /*判断快指针是否已经到达链表末尾，如果是则说明链表中没有环*/
            if (fast == null || fast.next == null) {
                return false;
            }
            /*移动快慢指针*/
            slow = slow.next;
            fast = fast.next.next;
        }
        /*如果快指针追上了慢指针，则说明链表中有环*/
        return true;
    }

    public static void main(String[] args) {
        /*构建一个有环链表*/
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        /*这里将链表尾部指向 node1，形成一个环*/
        node4.next = node1;

        /*检测链表是否有环*/
        boolean hasCycle = new CycleList().hasCycle(head);

        if (hasCycle) {
            System.out.println("该链表有环");
        } else {
            System.out.println("该链表无环");
        }
    }
}
