package org.zyf.javabasic.letcode.hot100.list;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @program: zyfboot-javabasic
 * @description: 回文链表
 * @author: zhangyanfeng
 * @create: 2024-08-22 00:03
 **/
public class PalindromeSolution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true; // 空链表或只有一个节点的链表是回文的
        }

        // 快慢指针找到链表的中间
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 反转链表的后半部分
        ListNode secondHalf = reverseList(slow);
        ListNode firstHalf = head;

        // 比较前半部分和后半部分
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        // 恢复链表的原始状态
        reverseList(slow);

        return true;
    }

    // 反转链表的函数
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public static void main(String[] args) {
        PalindromeSolution solution = new PalindromeSolution();

        // 测试用例 1: 回文链表
        System.out.println("Test Case 1: [1, 2, 2, 1]");
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(2);
        head1.next.next.next = new ListNode(1);
        System.out.println("Is palindrome: " + solution.isPalindrome(head1));

        // 测试用例 2: 非回文链表
        System.out.println("Test Case 2: [1, 2]");
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        System.out.println("Is palindrome: " + solution.isPalindrome(head2));

        // 测试用例 3: 单节点链表
        System.out.println("Test Case 3: [1]");
        ListNode head3 = new ListNode(1);
        System.out.println("Is palindrome: " + solution.isPalindrome(head3));

        // 测试用例 4: 空链表
        System.out.println("Test Case 4: []");
        ListNode head4 = null;
        System.out.println("Is palindrome: " + solution.isPalindrome(head4));
    }
}
