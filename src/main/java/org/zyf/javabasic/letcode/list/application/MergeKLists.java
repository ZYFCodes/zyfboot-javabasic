package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

import java.util.PriorityQueue;

/**
 * @author yanfengzhang
 * @description 题目描述：合并k个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 示例：
 * 输入:[1->4->5,1->3->4,2->6]    输出: 1->1->2->3->4->4->5->6
 * 来源：力扣（LeetCode）  链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * @date 2023/5/8  23:45
 */
public class MergeKLists {

    /**
     * 要实现合并多个有序链表，我们可以使用分治法。
     * 具体来说，我们可以将所有链表两两合并，直到所有链表都被合并为止。
     * 如果有奇数个链表，我们可以将最后一个链表和前面合并后得到的链表再次合并。
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            /*判断特殊情况*/
            return null;
        }
        int n = lists.length;
        /*当链表数量大于1时，不断循环*/
        while (n > 1) {
            /*计算本轮需要合并的链表数量*/
            int k = (n + 1) / 2;
            /*每次循环合并相邻的两个链表*/
            for (int i = 0; i < n / 2; i++) {
                lists[i] = mergeTwoLists(lists[i], lists[i + k]);
            }
            /*将链表数量更新为本轮合并后的链表数量*/
            n = k;
        }
        /*返回合并后的链表*/
        return lists[0];
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        /*创建dummy节点*/
        ListNode dummy = new ListNode(-1);
        /*cur指针指向dummy*/
        ListNode cur = dummy;
        /*当l1和l2都不为空时，不断循环*/
        while (l1 != null && l2 != null) {
            /*如果l1的值小于l2的值*/
            if (l1.val < l2.val) {
                /*将l1接在新链表的尾部*/
                cur.next = l1;
                /*将l1指针指向下一个节点*/
                l1 = l1.next;
            }
            /*如果l2的值小于等于l1的值*/
            else {
                /*将l2接在新链表的尾部*/
                cur.next = l2;
                /*将l2指针指向下一个节点*/
                l2 = l2.next;
            }
            /*将tail指针移动到新链表的尾部*/
            cur = cur.next;
        }
        /*如果l1还有剩余节点*/
        if (l1 != null) {
            /*将剩余节点接在新链表的尾部*/
            cur.next = l1;
        }
        /*如果l2还有剩余节点*/
        if (l2 != null) {
            /*将剩余节点接在新链表的尾部*/
            cur.next = l2;
        }
        /*返回合并后的链表*/
        return dummy.next;
    }

    /**
     * 除了使用分治法外，我们还可以使用优先队列（Priority Queue）来合并多个有序链表。
     * 具体来说，我们可以将所有链表的头节点放入一个优先队列中，
     * 每次取出队列中值最小的节点，并将它的下一个节点插入队列中。
     * 重复这个过程直到队列为空，这样就得到了合并后的链表。
     */
    public ListNode mergeKListsByPriorityQueue(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            /*判断特殊情况*/
            return null;
        }
        /*创建优先队列*/
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        /*将所有链表的头节点加入优先队列中*/
        for (ListNode head : lists) {
            if (head != null) {
                pq.offer(head);
            }
        }
        /*创建dummy节点*/
        ListNode dummy = new ListNode(-1);
        /*tail指针指向dummy*/
        ListNode tail = dummy;
        /*当优先队列不为空时，不断循环*/
        while (!pq.isEmpty()) {
            /*取出优先队列中最小的节点*/
            ListNode node = pq.poll();
            /*将这个节点接在新链表的尾部*/
            tail.next = node;
            /*将tail指针移动到新链表的尾部*/
            tail = tail.next;
            /*如果这个节点有下一个节点*/
            if (node.next != null) {
                /*将下一个节点加入优先队列中*/
                pq.offer(node.next);
            }
        }
        /*返回合并后的链表*/
        return dummy.next;
    }
}
