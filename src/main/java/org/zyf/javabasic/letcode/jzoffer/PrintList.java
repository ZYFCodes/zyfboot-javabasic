package org.zyf.javabasic.letcode.jzoffer;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 输入一个链表的头节点，从尾到头反过来打印出每个节点的值。
 * @date 2023/6/6  23:46
 */
public class PrintList {
    /**
     * 这道题要求从尾到头打印链表，可以使用递归或者辅助栈来实现。
     * 1.	递归方法：
     * •	递归函数的作用是先递归打印链表的下一个节点，再打印当前节点的值。
     * •	当遇到空节点时，即到达链表尾部，开始回溯并打印节点的值。
     * 2.	辅助栈方法：
     * •	遍历链表，将每个节点的值依次压入栈中。
     * •	遍历完成后，依次从栈中弹出节点的值，即可实现从尾到头打印。
     */
    public void printListReversingly(ListNode head) {
        if (head != null) {
            if (head.next != null) {
                printListReversingly(head.next);
            }
            System.out.println(head.val);
        }
    }

    public static void main(String[] args) {
        PrintList solution = new PrintList();

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

        // 打印链表
        solution.printListReversingly(head);
    }
}
