package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 给定一个单链表和两个整数 left 和 right，
 * 反转链表中从第 left 个节点到第 right 个节点的部分。
 * 例如，对于链表 1->2->3->4->5，left = 2，right = 4，
 * 则反转链表中从第 2 个节点到第 4 个节点的部分，得到链表 1->4->3->2->5。
 * @date 2022/1/3  22:45
 */
public class ReverseLinkedListBetween {

    /**
     * 最优解法是使用迭代法，在一次遍历中完成链表反转的某一部分。其时间复杂度为 O(n)，其中 n 是链表的节点数。
     * 在迭代法中，我们需要使用三个指针：prev、curr 和 next，分别表示当前节点的前一个节点、当前节点和下一个节点。
     * 具体步骤如下：
     * * 定位到要反转部分的前一个节点，记为 prev。开始时，prev 指向虚拟头节点(dummy)。
     * * 将 curr 指向 prev 的下一个节点，即要反转部分的第一个节点。
     * * 反转从第 left 到第 right 的部分，类似反转整个链表的过程。在反转过程中，使用一个临时指针 next 来记录当前节点的下一个节点，以保证反转后能继续遍历。
     * * 将 prev 的 next 指向反转后的部分的头节点，将反转部分的尾节点的 next 指向 next。
     * * 返回虚拟头节点(dummy)的 next，即反转链表的头节点。
     * 该算法只需要进行一次遍历，每次操作都只涉及常数个指针变量，因此时间复杂度为 O(n)。
     * 空间复杂度为 O(1)，因为只使用了常数个额外指针来完成反转操作。这使得这种解法成为最优解法。
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        // 创建一个虚拟头节点(dummy)，用于处理头节点的反转
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 定位要反转部分的前一个节点，开始时指向虚拟头节点(dummy)
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        // curr指向prev的下一个节点，即要反转部分的第一个节点
        ListNode curr = prev.next;

        // 反转从第 left 到第 right 的部分
        for (int i = 0; i < right - left; i++) {
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }

        // 返回虚拟头节点(dummy)的下一个节点，即反转链表的头节点
        return dummy.next;
    }

    public static void main(String[] args) {
        // 构建一个测试链表：1->2->3->4->5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int left = 2;
        int right = 4;
        // 调用反转链表部分的函数
        ListNode reversedHead = reverseBetween(head, left, right);

        // 验证反转部分后的链表：1->4->3->2->5
        ListNode node = reversedHead;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        // 输出结果为：1 4 3 2 5
    }

}
