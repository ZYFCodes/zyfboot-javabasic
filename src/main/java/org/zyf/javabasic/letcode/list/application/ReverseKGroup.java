package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 示例：输入：head = [1,2,3,4,5], k = 2.       输出：[2,1,4,3,5]
 * 输入：head = [1,2,3,4,5], k = 3        输出：[3,2,1,4,5]
 * <p>
 * 提示：
 * 链表中节点的数量在范围 sz 内，1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 * @date 2023/4/5  23:32
 */
public class ReverseKGroup {

    /*解法：
     * 使用迭代法实现链表翻转，每 k 个节点为一组，处理完后将前后两组连接起来。
     * 具体实现时，使用 pre 指向待翻转链表的前一个节点，end 指向待翻转链表的尾部节点，
     * 然后遍历链表，处理 k 个节点为一组的子链表，将其翻转，并将翻转后的头节点与前面的子链表连接，
     * 并将 pre 指向下一组子链表的头节点的前一个节点，end 指向下一组子链表的尾部节点，
     * 直到链表遍历完成。
     *
     * 时间复杂度：O(n)，其中 n 是链表的长度。head 节点会在 O(n/k) 段中被翻转，每次翻转的时间复杂度是 O(k)。
     * 空间复杂度：O(1)。*/
    public ListNode reverseKGroup(ListNode head, int k) {
        /*如果链表为空、链表只有一个元素或k=1，则直接返回原链表*/
        if (head == null || head.next == null || k == 1) {
            return head;
        }

        /*dummy节点可以避免很多判断和特殊处理，所以一般在链表题目中都会用到*/
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        /*每一段翻转前的前一个节点*/
        ListNode pre = dummy;
        /*每一段翻转后的尾节点*/
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                /*将end指针移动到需要翻转的段的最后一个节点*/
                end = end.next;
            }
            if (end == null) {
                break;
            }

            /*每一段翻转前的第一个节点*/
            ListNode start = pre.next;
            /*记录下一段翻转前的第一个节点*/
            ListNode next = end.next;
            /*切断当前段和下一段的连接，便于翻转当前段*/
            end.next = null;

            /*翻转当前段*/
            pre.next = reverse(start);
            /*连接上一段和当前段，连接当前段和下一段*/
            start.next = next;

            /*将pre、end移动到下一段翻转前的位置*/
            pre = start;
            end = start;
        }

        return dummy.next;
    }

    /**
     * 翻转链表
     */
    private ListNode reverse(ListNode head) {
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
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        int k = 2;
        System.out.println("Original List: " + printLinkedList(head));
        head = new ReverseKGroup().reverseKGroup(head, k);
        System.out.println("Reversed List: " + printLinkedList(head));
    }

    private static String printLinkedList(ListNode head) {
        StringBuilder res = new StringBuilder();
        while (head != null) {
            res.append(head.val).append(" -> ");
            head = head.next;
        }
        res.append("null");
        return res.toString();
    }


}
