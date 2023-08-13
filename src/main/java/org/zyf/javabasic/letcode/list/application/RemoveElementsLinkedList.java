package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 从链表中删除所有指定值的节点。
 * @date 2022/2/3  22:40
 */
public class RemoveElementsLinkedList {

    /**
     * 最优解法是迭代法，其时间复杂度为 O(n)，其中 n 是链表的节点数。
     * 在迭代法中，我们使用两个指针 prev 和 curr 来遍历链表。初始时，prev 指向虚拟头节点(dummy)，curr 指向链表的头节点。
     * 我们需要遍历整个链表，检查每个节点的值是否等于目标值 val。
     * 如果当前节点的值等于目标值，我们将 prev 的 next 指针指向 curr 的下一个节点，从而跳过当前节点，实现删除操作。
     * 如果当前节点的值不等于目标值，我们更新 prev 为当前节点，并将 curr 指向下一个节点，继续遍历。
     * 通过这样的遍历过程，我们可以删除所有值为目标值 val 的节点，最后返回虚拟头节点(dummy)的 next，即删除指定元素后的链表的头节点。
     * 该算法遍历了链表一次，每次删除操作只需要常数时间，因此时间复杂度为 O(n)。
     * 空间复杂度为 O(1)，因为只使用了常数个额外指针来完成删除操作。这使得这种解法成为最优解法。
     */
    public static ListNode removeElements(ListNode head, int val) {
        // 创建一个虚拟头节点(dummy)，用于处理头节点的删除
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // prev指向虚拟头节点(dummy)，curr指向当前节点
        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null) {
            if (curr.val == val) {
                // 删除当前节点，将prev与curr的下一个节点相连
                prev.next = curr.next;
            } else {
                // 当前节点值不等于目标值，更新prev为当前节点
                prev = curr;
            }
            // 更新curr为下一个节点
            curr = curr.next;
        }

        // 返回虚拟头节点(dummy)的下一个节点，即删除指定元素后的链表的头节点
        return dummy.next;
    }

    public static void main(String[] args) {
        // 构建一个测试链表：1->2->6->3->4->5->6
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);

        int target = 6;
        // 调用删除指定元素的函数
        ListNode modifiedHead = removeElements(head, target);

        // 验证删除指定元素后的链表：1->2->3->4->5
        ListNode node = modifiedHead;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        // 输出结果为：1 2 3 4 5
    }
}
