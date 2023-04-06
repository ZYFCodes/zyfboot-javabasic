package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 示例 1:输入: 1->1->2.    输出: 1->2
 * 示例 2:输入: 1->1->2->3->3.  输出: 1->2->3
 * @date 2023/4/5  23:14
 */
public class DeleteDuplicates {

    /**
     * 题目描述：删除链表中的重复元素
     * 思路：使用双指针，判断当前节点的值是否和下一个节点的值相同，
     * 若相同则将当前节点指向下一个节点的下一个节点，直到当前节点和下一个节点的值不同
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head 链表头结点
     * @return 删除重复元素后的链表头结点
     */
    public ListNode deleteDuplicates(ListNode head) {
        /*判断链表是否为空或只有一个节点*/
        if (head == null || head.next == null) {
            return head;
        }
        /*设置哑节点dummy，指向头结点head*/
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        /*定义双指针，cur指向当前节点，遍历整个链表*/
        ListNode cur = head;
        while (cur != null) {
            /*若当前节点和下一个节点的值相同，将当前节点指向下一个节点的下一个节点，直到当前节点和下一个节点的值不同*/
            while (cur.next != null && cur.val == cur.next.val) {
                cur.next = cur.next.next;
            }
            cur = cur.next;
        }
        /*返回哑节点dummy的下一个节点，即删除重复元素后的链表头结点*/
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode newHead = new DeleteDuplicates().deleteDuplicates(head);

        while (newHead != null) {
            System.out.print(newHead.val + "->");
            newHead = newHead.next;
        }
    }
}
