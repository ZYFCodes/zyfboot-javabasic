package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给定一个单链表和两个整数 left 和 right，判断从第 left 个节点到第 right 个节点的部分是否是回文链表。
 * 例如，对于链表 1->2->3->2->1，left = 2，right = 4，
 * 则从第 2 个节点到第 4 个节点的部分 2->3->2 是一个回文链表，因此返回 true。
 * @date 2023/5/3  00:50
 */
public class PalindromeSubLinkedList {

    /**
     * 这道题的解题思路如下：
     * 首先，我们需要找到从第 left 个节点到第 right 个节点的部分链表。
     * 找到第 left 个节点的前一个节点 leftPrev 和第 right 个节点 rightNode。
     * 截取链表，将 leftPrev 的 next 指针指向 null，将 rightNode 的 next 指针指向 null，这样就得到了从第 left 个节点到第 right 个节点的部分链表。
     * 反转从第 left 个节点到第 right 个节点的部分链表，得到反转后的链表 reverseNode。
     * 比较原链表中从第 left 个节点到第 right 个节点的部分链表和反转后的链表是否相等，如果相等，则说明是回文链表。
     * 最后，将截取的链表恢复原状，即将 leftPrev 的 next 指针指向 reverseNode，将 reverseNode 的 next 指针指向 rightNext。
     * 返回判断结果。
     * 这样，我们就可以判断从第 left 个节点到第 right 个节点的部分是否是回文链表。
     * <p>
     * 这个算法的时间复杂度为O(n)，其中n是链表的长度。
     * 这是因为我们需要遍历链表找到第left个节点和第right个节点，然后截取链表和反转链表都需要O(n)的时间复杂度。
     * 最后，我们需要比较两个链表的值是否相等，这也需要O(n)的时间复杂度。
     * 空间复杂度为O(1)，因为我们只使用了常数个额外的指针来存储节点的引用，没有使用额外的数据结构。
     * 综上所述，该算法的时间复杂度为O(n)，空间复杂度为O(1)。
     *
     * @param head  链表头节点
     * @param left  左边界
     * @param right 右边界
     * @return true：是回文链表；false：不是回文链表
     */
    public static boolean isPalindrome(ListNode head, int left, int right) {
        if (head == null) {
            return false;
        }

        // 找到第 left 个节点的前一个节点
        ListNode leftPrev = null;
        ListNode cur = head;
        for (int i = 1; i < left; i++) {
            leftPrev = cur;
            cur = cur.next;
        }

        // 找到第 right 个节点
        ListNode rightNode = cur;
        for (int i = left; i < right; i++) {
            rightNode = rightNode.next;
        }

        // 截取链表
        ListNode leftNode = leftPrev == null ? head : leftPrev.next;
        ListNode rightNext = rightNode.next;
        rightNode.next = null;

        // 反转链表
        ListNode reverseNode = reverseList(leftNode);

        // 判断是否回文
        boolean isPalindrome = true;
        ListNode p1 = leftNode;
        ListNode p2 = reverseNode;
        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) {
                isPalindrome = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 恢复链表
        leftNode.next = rightNext;
        if (leftPrev != null) {
            leftPrev.next = reverseNode;
        } else {
            head = reverseNode;
        }

        return isPalindrome;
    }

    /**
     * 反转链表.
     *
     * @param head 链表头节点
     * @return 反转后的链表头节点
     */
    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }


    public static void main(String[] args) {
        // 构建一个测试链表：1->2->3->2->1
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);

        int left = 2;
        int right = 4;
        // 调用判断是否为回文链表的函数
        boolean result = isPalindrome(head, left, right);

        // 验证结果为true，说明从第2个节点到第4个节点的部分2->3->2是回文链表
        System.out.println("Is it a palindrome? " + result);
    }

}
