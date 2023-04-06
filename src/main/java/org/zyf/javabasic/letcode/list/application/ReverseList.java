package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 反转一个单链表。
 * 示例：输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * @date 2023/4/5  23:07
 */
public class ReverseList {
    /**
     * 对于反转链表这道题目，其高效最优解法是使用迭代实现。具体思路如下：
     * 1 初始化三个指针，分别为 prev、curr 和 next。初始化时，prev 和 curr 指向 NULL，next 指向链表头节点 head。
     * 2 循环遍历链表，直到遍历完整个链表。循环中进行如下操作：
     * 将 curr 的 next 指针指向 prev，实现 curr 的指针反转。
     * 将 prev 和 curr 往后移动一位，即 prev 指向 curr，curr 指向 next。
     * 将 next 指向 curr 的下一个节点，即 next 指向 curr->next。
     * 3 当遍历完整个链表后，prev 就指向了原链表的最后一个节点，也就是反转后的链表头节点。因此，我们返回 prev 即可。
     */
    public ListNode reverseList(ListNode head) {
        /*初始化前驱节点为NULL*/
        ListNode prev = null;
        /*初始化当前节点为NULL*/
        ListNode curr = null;
        /*初始化后驱节点为头节点*/
        ListNode next = head;

        /*遍历列表*/
        while (next != null) {
            /*当前节点为后继节点*/
            curr = next;
            /*后继节点后移*/
            next = next.next;
            /*反转当前节点的指针*/
            curr.next = prev;
            /*前驱节点后移*/
            prev = curr;
        }

        /*返回反转后的节点*/
        return prev;
    }

    public static void main(String[] args) {
        /*创建一个链表：1->2->3->4->5->NULL*/
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = null;

        /*反转链表*/
        ListNode reversed = new ReverseList().reverseList(head);

        /*输出反转后的链表：5->4->3->2->1->NULL*/
        while (reversed != null) {
            System.out.print(reversed.val + "->");
            reversed = reversed.next;
        }
        System.out.println("NULL");
    }
}
