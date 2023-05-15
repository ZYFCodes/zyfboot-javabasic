package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给你一个链表，删除链表中倒数第 n 个节点，并且返回链表的头结点。
 * 示例：输入：head = [1,2,3,4,5], n = 2.      输出：[1,2,3,5]
 * 提示：
 * 链表中节点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= s
 * @date 2023/4/5  23:56
 */
public class RemoveKthFromEnd {

    /**
     * 最优的解法是使用快慢指针，快指针先走n步，然后快慢指针一起走，当快指针到达链表末尾时，
     * 慢指针就是要删除的节点的前一个节点。接着，通过改变指针的指向，将慢指针指向的节点删除即可。
     * <p>
     * 具体步骤如下：
     * 定义两个指针：快指针和慢指针，初始都指向头节点。
     * 快指针先走n步，如果此时快指针已经到达了链表末尾，说明要删除的是头节点，直接返回head->next即可。
     * 否则，让快慢指针一起走，直到快指针到达链表末尾。
     * 此时，慢指针指向的节点就是要删除的节点的前一个节点。
     * 改变慢指针的next指向即可删除节点。
     * 需要注意的是，如果要删除的是头节点，需要特殊处理。
     * <p>
     * 时间复杂度为O(n)，空间复杂度为O(1)。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive integer");
        }
        /*创建 dummy 节点，用于处理删除头节点的情况*/
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        /*创建 slow 和 fast 指针，初始指向 dummy 节点*/
        ListNode slow = dummy;
        ListNode fast = dummy;
        /*fast 指针先向前移动 n 步*/
        for (int i = 0; i < n; i++) {
            /*如果 fast 已经移动到了链表末尾，但还未移动 n 步，说明 k 的值超出了链表的长度，抛出异常*/
            if (fast.next == null) {
                throw new IllegalArgumentException("n must be less than or equal to the length of the list");
            }
            fast = fast.next;
        }
        /*同时移动 slow 和 fast 指针，直到 fast 指向链表末尾*/
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        /*删除 slow 指针指向的节点*/
        slow.next = slow.next.next;
        /*返回头节点*/
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int n = 2;
        System.out.println("原链表：" + head.toString());
        ListNode newHead = new RemoveKthFromEnd().removeNthFromEnd(head, n);
        System.out.println("删除倒数第" + n + "个节点后的链表：" + newHead.toString());
    }

}
