package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 请实现一个函数，复制一个复杂链表。
 * 在复杂链表中，每个节点除了有一个next指针指向下一个节点，
 * 还有一个random指针指向链表中的任意节点或者null。
 * @date 2023/6/6  00:52
 */
public class ComxCopyList {
    /**
     * 要复制一个复杂链表，需要考虑节点的复制以及random指针的复制。可以采用以下步骤进行复制：
     * 1.	遍历原链表，复制每个节点，并将复制后的节点插入到原节点的后面。例如，对于原链表节点A，复制后的节点为A’，则将A’插入到A后面。
     * 2.	遍历原链表，复制random指针。对于每个复制后的节点A’，其对应的原节点为A，如果A的random指针指向节点B，则A’的random指针指向B的复制节点B’。
     * 3.	将复制后的链表拆分成两个链表，一个是原链表，一个是复制链表。遍历原链表，根据每个节点的位置关系，将其从链表中断开，形成两个独立的链表。
     */
    public ListNode copyRandomList(ListNode head) {
        if (head == null) {
            return null;
        }

        // 复制每个节点并插入到原节点后面
        ListNode cur = head;
        while (cur != null) {
            ListNode copyNode = new ListNode(cur.val);
            copyNode.next = cur.next;
            cur.next = copyNode;
            cur = copyNode.next;
        }

        // 复制random指针
        cur = head;
        while (cur != null) {
            ListNode copyNode = cur.next;
            if (cur.random != null) {
                copyNode.random = cur.random.next;
            }
            cur = copyNode.next;
        }

        // 拆分成两个链表
        cur = head;
        ListNode newHead = head.next;
        while (cur != null) {
            ListNode copyNode = cur.next;
            cur.next = copyNode.next;
            if (copyNode.next != null) {
                copyNode.next = copyNode.next.next;
            }
            cur = cur.next;
        }

        return newHead;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode random;

        ListNode(int val) {
            this.val = val;
        }
    }

    public void main(String[] args) {
        ComxCopyList solution = new ComxCopyList();

        // 构建复杂链表
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        head.random = node3;
        node2.random = node5;
        node4.random = node2;

        // 打印链表
        ListNode cur = copyRandomList(head);
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
