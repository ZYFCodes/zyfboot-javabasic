package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给定一个排序链表（默认正整数），删除所有含有重复数字的节点，只保留原始链表中没有重复出现的数字。
 * 示例 1:输入:1->1->2->2->3->4->4 输出：3
 * 示例 2:输入: 1->1->2->3->3.输出: 2
 * 示例 3:输入:1->2 ->2 ->2 ->5 ->2 ->3 ->3 ->9,输出1 ->5 ->2 ->9
 * * 注意
 * * 1.不考虑删除之后在连续重复的元素，如2 ->3 ->3 ->2，处理后为2 ->2
 * * 2.空间复杂度O(1)
 * * 写一个函数实现该功能
 * @date 2023/7/28  23:53
 */
public class DeleteDuplicatesII {

    /**
     * 最优解法是使用迭代的方法，在一次遍历中删除连续重复的元素，同时保持空间复杂度为 O(1)。
     * 这可以通过双指针来实现。一个指针用于表示当前已处理的部分，另一个指针用于遍历链表。具体步骤如下：
     * 1. 创建一个虚拟头节点 `dummy`，将其 `next` 指向链表的头节点 `head`，这样可以避免处理头节点时的特殊情况。
     * 2. 定义两个指针 `prev` 和 `curr`，初始时都指向虚拟头节点 `dummy`。
     * 3. 使用 `curr` 指针进行遍历，如果当前节点与下一个节点的值相同，则进入循环，继续移动 `curr` 指针，直到找到一个不同值的节点或链表结束。
     * 4. 检查 `curr` 与 `prev` 之间是否有重复元素。如果没有，将 `prev` 指针后移一位；如果有重复元素，将 `prev` 的 `next` 指针直接连接到 `curr` 的下一个节点，跳过中间的重复部分。
     * 5. 继续移动 `curr` 指针到下一个节点，并重复步骤 3 和 4，直到遍历完整个链表。
     * 6. 返回虚拟头节点的 `next`，即处理后的链表。
     */
    public ListNode deleteDuplicates(ListNode head) {
        // 创建虚拟头节点，简化删除操作
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // prev指向不重复部分的尾节点
        ListNode prev = dummy;
        // curr用于遍历整个链表
        ListNode curr = head;

        while (curr != null) {
            boolean isDuplicate = false;

            // 查找连续重复的元素
            while (curr.next != null && curr.val == curr.next.val) {
                curr = curr.next;
                isDuplicate = true;
            }

            // 如果有重复元素，跳过中间部分，连接prev和curr之间
            if (isDuplicate) {
                prev.next = curr.next;
            } else {
                // 如果没有重复元素，更新prev指向
                prev = prev.next;
            }

            // 移动curr指针到下一个节点
            curr = curr.next;
        }

        // 返回处理后的链表
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode newHead = new DeleteDuplicatesII().deleteDuplicates(head);
        newHead.traverseList(newHead);

        // 创建链表：1 -> 2 -> 2 -> 2 -> 5 -> 2 -> 3 -> 3 -> 9
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(2);
        head2.next.next.next = new ListNode(2);
        head2.next.next.next.next = new ListNode(5);
        head2.next.next.next.next.next = new ListNode(2);
        head2.next.next.next.next.next.next = new ListNode(3);
        head2.next.next.next.next.next.next.next = new ListNode(3);
        head2.next.next.next.next.next.next.next.next = new ListNode(9);

        ListNode newHead2 = new DeleteDuplicatesII().deleteDuplicates(head2);
        newHead2.traverseList(newHead2);

        // 创建链表：2 -> 2 -> 3
        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(2);
        head3.next.next = new ListNode(3);
        ListNode newHead3 = new DeleteDuplicatesII().deleteDuplicates(head3);
        newHead3.traverseList(newHead3);

        // 创建链表：1 -> 2 -> 2
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(2);
        // ... 继续添加节点 ...

        ListNode result1 = new DeleteDuplicatesII().deleteDuplicates(head1);

        // 输出链表：1 -> null
        result1.traverseList(result1);
    }
}
