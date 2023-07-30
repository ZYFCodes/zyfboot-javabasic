package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给定一个排序链表（默认正整数），删除所有含有重复数字的节点，只保留原始链表中没有重复出现的数字
 * 例如输入1-1-2-2-3-4-4 输出3
 * @date 2023/7/28  23:53
 */
public class DeleteDuplicatesII {

    /**
     * 最优解法是使用迭代遍历链表的方式，同时使用辅助指针和判断条件来删除重复节点。具体步骤如下：
     * 1. 创建一个虚拟头节点 dummy，让 dummy.next 指向原始链表的头节点，用于处理头节点重复的情况。
     * 2. 使用两个指针，pre 和 cur，分别指向虚拟头节点和原始链表的头节点。
     * 3. 使用一个循环来遍历链表，判断 cur 是否和下一个节点值相同。
     * 4. 如果 cur 的值和下一个节点值相同，则进入循环，将 cur 不断后移，直到 cur 指向的节点值和下一个节点值不相同或链表末尾。
     * 5. 如果 cur 和 pre 之间有重复节点（即 cur 不等于 pre.next），说明这段区间内的节点值是重复的，需要删除这段区间。
     * 6. 删除节点的方法是让 pre 的 next 指向 cur 的 next。
     * 7. 否则，如果 cur 和 pre 之间没有重复节点，说明这段区间内的节点值是没有重复的，可以保留这段区间。
     * 8. 最后，返回虚拟头节点的 next，即为删除重复节点后的链表头。
     */
    public ListNode deleteDuplicates2(ListNode head) {
        // 创建虚拟头节点 dummy，指向原始链表的头节点，用于处理头节点重复的情况
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // pre 指针指向虚拟头节点，用于记录未重复的最后一个节点
        ListNode pre = dummy;
        // cur 指针指向原始链表的头节点
        ListNode cur = head;

        while (cur != null) {
            // 迭代判断是否有重复节点，cur 指向重复节点的最后一个节点
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }

            // 如果 pre.next 不等于 cur，说明中间有重复节点，需要删除这段区间
            if (pre.next != cur) {
                pre.next = cur.next;
            } else {
                // 否则，说明这段区间内的节点值是没有重复的，可以保留这段区间
                pre = cur;
            }

            // 继续遍历下一个节点
            cur = cur.next;
        }

        // 返回虚拟头节点的 next，即为删除重复节点后的链表头
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
