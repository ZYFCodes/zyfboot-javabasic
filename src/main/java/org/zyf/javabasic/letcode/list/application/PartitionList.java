package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 * 示例：输入：head = 1->4->3->2->5->2, x = 3.   输出：1->2->2->4->3->5
 * 解释：小于 3 的元素放在左边，大于等于 3 的元素放在右边。
 * @date 2023/4/5  23:11
 */
public class PartitionList {

    /**
     * 最优解法的时间复杂度为O(n)，空间复杂度为O(1)。
     * 具体而言，可以维护两个指针smaller和larger，它们分别指向分隔后链表中值小于x和不小于x的节点。
     * 初始时，smaller和larger都为null。
     * 遍历原始链表，对于每个节点，如果节点的值小于x，则将其插入到smaller链表的末尾；
     * 否则，将其插入到larger链表的末尾。
     * 最后，将smaller链表的末尾指向larger链表的开头即可。
     */
    public ListNode partition(ListNode head, int x) {
        ListNode smaller = new ListNode(0);
        ListNode larger = new ListNode(0);
        ListNode smallerHead = smaller;
        ListNode largerHead = larger;

        while (head != null) {
            if (head.val < x) {
                /*如果节点的值小于 x，则插入到 smaller 链表的末尾*/
                smaller.next = head;
                smaller = smaller.next;
            } else {
                /*否则插入到 larger 链表的末尾*/
                larger.next = head;
                larger = larger.next;
            }
            head = head.next;
        }
        /*将 larger 链表末尾设置为 null，否则会出现循环引用的情况*/
        larger.next = null;
        /*将 smaller 链表的末尾指向 larger 链表的开头*/
        smaller.next = largerHead.next;

        return smallerHead.next;
    }

    public static void main(String[] args) {
        /*构造链表 1->4->3->2->5->2*/
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);

        /*分隔链表*/
        int x = 3;
        ListNode newHead = new PartitionList().partition(head, x);

        /*验证结果*/
        while (newHead != null) {
            System.out.print(newHead.val + " ");
            newHead = newHead.next;
        }
    }
}
