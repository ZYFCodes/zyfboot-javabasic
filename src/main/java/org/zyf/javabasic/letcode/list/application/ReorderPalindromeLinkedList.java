package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给定一个回文链表，将其重新排列，要求空间复杂度为 O(1)。
 * 示例 1:
 * 输入: 1 -> 2 -> 3 -> 4 -> 5 -> 4 -> 3 -> 2 -> 1
 * 输出: 1 -> 1 -> 2 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
 * 在这个例子中，给定的回文链表为 1 -> 2 -> 3 -> 4 -> 5 -> 4 -> 3 -> 2 -> 1。
 * 重排后，将其变为1 -> 1 -> 2 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5。
 * 对于这个问题，要求空间复杂度为 O(1)，意味着我们不能使用额外的数据结构来存储链表的中间部分或者逆序后的后半部分。
 * @date 2023/8/2  22:03
 */
public class ReorderPalindromeLinkedList {

    /**
     * 回文链表重排问题可以通过以下步骤解决，要求空间复杂度为O(1)：
     * * 使用快慢指针找到链表的中间节点。
     * * 反转链表的后半部分。
     * * 将后半部分的节点依次插入到前半部分的节点之间。
     * 以下是具体的步骤分析：
     * * 使用快慢指针找到链表的中间节点。快指针每次走两步，慢指针每次走一步，当快指针到达链表末尾时，慢指针就指向链表的中间节点。
     * * 反转链表的后半部分。从中间节点开始，将链表的后半部分反转，得到一个新的链表。
     * * 将后半部分的节点依次插入到前半部分的节点之间。将前半部分和反转后的后半部分依次交叉合并。
     * 这样，就可以重新排列回文链表，满足题目要求的空间复杂度为O(1)。
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // 使用快慢指针找到链表的中间节点
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 反转链表的后半部分
        ListNode prev = null, current = slow;
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        // 将后半部分的节点依次插入到前半部分的节点之间
        ListNode firstHalf = head, secondHalf = prev;
        while (secondHalf.next != null) {
            ListNode nextFirst = firstHalf.next;
            ListNode nextSecond = secondHalf.next;
            firstHalf.next = secondHalf;
            secondHalf.next = nextFirst;
            firstHalf = nextFirst;
            secondHalf = nextSecond;
        }
    }

    public static void main(String[] args) {
        // 创建一个回文链表 1 -> 2 -> 3 -> 4 -> 5 -> 4 -> 3 -> 2 -> 1
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next.next.next.next = new ListNode(1);

        // 打印原始链表
        System.out.println("原始链表：");
        printLinkedList(head);

        // 重排链表
        ReorderPalindromeLinkedList reorder = new ReorderPalindromeLinkedList();
        reorder.reorderList(head);

        // 打印重排后的链表
        System.out.println("\n重排后的链表：");
        printLinkedList(head);
    }

    private static void printLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

}
