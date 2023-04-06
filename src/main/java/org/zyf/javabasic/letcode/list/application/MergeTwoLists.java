package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 合并两个有序链表，返回一个新的链表，新链表是这两个链表中的所有节点按照从小到大的顺序排列而成。
 * 示例:输入：1->2->4, 1->3->4.      输出：1->1->2->3->4->4
 * @date 2023/4/5  23:21
 */
public class MergeTwoLists {

    /**
     * 首先，我们定义一个新的链表作为合并后的链表，然后设置两个指针分别指向两个原始链表的头节点。
     * 我们每次比较两个指针指向节点的值，将较小的节点加入新的链表中，并将指针后移一位。
     * 重复这个过程，直到某一个指针为空，此时我们将另一个链表剩下的部分全部加入新链表中即可。
     * <p>
     * 这种解法的时间复杂度为O(min(m,n))，空间复杂度为O(1)，是最优解法之一。
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        /*定义一个哨兵节点，简化操作*/
        ListNode dummy = new ListNode(-1);
        /*定义一个指针，指向哨兵节点*/
        ListNode cur = dummy;

        /*循环比较两个链表中的节点，直到其中一个链表为空*/
        while (l1 != null && l2 != null) {
            /*如果l1的节点值小于等于l2的节点值，将l1的节点接入新链表中*/
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            }
            /*否则将l2的节点接入新链表中*/
            else {
                cur.next = l2;
                l2 = l2.next;
            }
            /*指针向后移动*/
            cur = cur.next;
        }

        /*如果l1链表还有剩余节点，则将其接入新链表中*/
        if (l1 != null) {
            cur.next = l1;
        }
        /*如果l2链表还有剩余节点，则将其接入新链表中*/
        if (l2 != null) {
            cur.next = l2;
        }

        /*返回哨兵节点的下一个节点，即为新链表的头节点*/
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode mergedList = new MergeTwoLists().mergeTwoLists(l1, l2);

        while (mergedList != null) {
            System.out.print(mergedList.val + " ");
            mergedList = mergedList.next;
        }
    }
}
