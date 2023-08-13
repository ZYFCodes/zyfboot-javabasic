package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @author yanfengzhang
 * @description 交换链表中相邻的节点，如1->2->3->4变成2->1->4->3
 * @date 2023/3/3  00:37
 */
public class SwapPairsLinkedList {

    /**
     * 最优解法是迭代法，其时间复杂度为 O(n)，其中 n 是链表的节点数。
     * 在迭代法中，我们使用三个指针来交换节点：prev、first_node 和 second_node。初始时，prev 指向虚拟头节点(dummy)，first_node 指向当前节点，second_node 指向 first_node 的下一个节点。
     * 首先判断 first_node 和 second_node 是否为空，若为空，则说明已经遍历到链表末尾或只有一个节点，不需要再进行交换。然后，执行节点交换的操作：
     * * prev.next 指向 second_node，使得 prev 和 first_node 之间的连接断开，prev 与 second_node 相连。
     * * first_node.next 指向 second_node.next，使得 first_node 和 second_node 之间的连接断开，first_node 指向 second_node 后面的节点。
     * * second_node.next 指向 first_node，使得 second_node 成为头节点，与 prev 成为相邻节点。
     * 接着，更新 prev、first_node 和 second_node 的指针位置，继续迭代下一个节点对的交换。最后返回虚拟头节点的下一个节点，即交换后的链表的头节点。
     * 该算法遍历了链表一次，每次交换操作只需要常数时间，因此时间复杂度为 O(n)。空间复杂度为 O(1)，因为只使用了常数个额外指针来完成交换操作。这使得这种解法成为最优解法。
     */
    public static ListNode swapPairs(ListNode head) {
        // 创建一个虚拟头节点(dummy)，用于处理头节点的交换
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // prev指向虚拟头节点(dummy)，firstNode指向当前节点，secondNode指向firstNode的下一个节点
        ListNode prev = dummy;
        ListNode firstNode = head;

        while (firstNode != null && firstNode.next != null) {
            ListNode secondNode = firstNode.next;

            // 交换节点
            prev.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            // 更新prev、firstNode和secondNode的指针位置
            prev = firstNode;
            firstNode = firstNode.next;
        }

        // 返回虚拟头节点(dummy)的下一个节点，即交换后的链表的头节点
        return dummy.next;
    }

    public static void main(String[] args) {
        // 构建一个测试链表：1->2->3->4
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        // 调用交换相邻节点的函数
        ListNode swappedHead = swapPairs(head);

        // 验证交换后的链表：2->1->4->3
        ListNode node = swappedHead;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        // 输出结果为：2 1 4 3
    }

}
