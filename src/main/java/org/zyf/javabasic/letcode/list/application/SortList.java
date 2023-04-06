package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给你链表的头节点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 进阶：你可以在 O(nlogn) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * 示例 1：输入：head = [4,2,1,3].         输出：[1,2,3,4]
 * 示例 2：输入：head = [-1,5,3,4,0].      输出：[-1,0,3,4,5]
 * 示例 3：输入：head = [].                输出：[]
 * <p>
 * 提示：链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 * @date 2023/4/5  23:25
 */
public class SortList {

    /**
     * 对链表进行归并排序
     *
     * @param head 链表头节点
     * @return 排序后的链表头节点
     */
    public ListNode sortList(ListNode head) {
        /*特判：当链表为空或者只有一个节点时，无需排序，直接返回*/
        if (head == null || head.next == null) {
            return head;
        }
        /*获取链表中间节点*/
        ListNode mid = getMiddle(head);
        /*对链表左半部分和右半部分分别进行归并排序*/
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        /*合并左半部分和右半部分*/
        return merge(left, right);
    }

    /**
     * 获取链表中间节点
     *
     * @param head 链表头节点
     * @return 链表中间节点
     */
    private ListNode getMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        /*快慢指针寻找中间节点，快指针一次移动两个节点，慢指针一次移动一个节点*/
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        /*将链表分为两部分，并将前一部分的尾节点的next置为null*/
        ListNode mid = slow.next;
        slow.next = null;
        return mid;
    }

    /**
     * 合并两个有序链表
     *
     * @param l1 第一个有序链表头节点
     * @param l2 第二个有序链表头节点
     * @return 合并后的有序链表头节点
     */
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        /*将l1和l2的节点逐个比较，将较小的节点接到cur的后面*/
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        /*将剩余节点连接到cur的后面*/
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        ListNode sorted = new SortList().sortList(head);
        while (sorted != null) {
            System.out.print(sorted.val + " ");
            sorted = sorted.next;
        }
    }

}
