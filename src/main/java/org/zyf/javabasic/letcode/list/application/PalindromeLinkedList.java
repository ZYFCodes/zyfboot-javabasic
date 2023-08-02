package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给定一个单链表，判断它是否是回文链表。
 * 示例 1:
 * 输入: 1 -> 2
 * 输出: false
 * 示例 2:
 * 输入: 1 -> 2 -> 2 -> 1
 * 输出: true
 * @date 2023/8/2  23:59
 */
public class PalindromeLinkedList {

    /**
     * 最优解法可以在O(n)的时间复杂度和O(1)的空间复杂度内判断链表是否是回文结构。这里给出一个步骤简述：
     * * 使用快慢指针找到链表的中间节点，可以通过快指针走两步，慢指针走一步的方式实现。当快指针到达链表末尾时，慢指针就指向链表的中间节点。
     * * 反转链表的后半部分，从中间节点开始到链表末尾的部分都需要反转。
     * * 比较链表的前半部分和反转后的后半部分是否完全相同。比较的过程可以使用两个指针分别遍历前半部分和后半部分进行逐个节点的比较。
     * 如果链表长度为奇数，中间节点不需要参与比较，直接跳过。通过这种方法，我们可以高效地判断给定链表是否是回文结构。
     * 这种解法的关键在于找到链表的中间节点并对后半部分进行反转。这样做的好处是节省了额外的空间，并且可以在一次遍历内完成判断。
     */
    public static boolean isPalindrome(ListNode head) {
        // 1. 使用快慢指针找到链表的中间节点
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. 反转链表的后半部分
        ListNode prev = null, current = slow;
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        // 3. 比较链表的前半部分和反转后的后半部分是否完全相同
        ListNode left = head, right = prev;
        while (right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }

        return true;
    }

    public static void main(String[] args) {
        // 创建一个回文链表 1 -> 2 -> 2 -> 1
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);

        boolean result = isPalindrome(head);
        // 输出结果为true
        System.out.println("Is the linked list palindrome? " + result);
    }
}
