package org.zyf.javabasic.letcode.list.application;

import org.zyf.javabasic.letcode.list.base.ListNode;

/**
 * @program: zyfboot-javabasic
 * @description: 一个单链表从表结尾开始每k个元素进行反转
 * 例如：0->1->2->3->4->5->6->7,k=3,
 * 反转后，0->1->4->3->2->7->6->5
 * @author: zhangyanfeng
 * @create: 2024-09-12 21:19
 **/
public class ReverseKGroupFromEnd {

    // 主函数，从链表尾部开始每k个元素进行翻转
    public static ListNode reverseKFromEnd(ListNode head, int k) {
        if (head == null || k <= 1) return head;

        // 1. 计算链表长度
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        // 2. 如果链表长度小于等于k，则不进行反转
        if (length <= k) {
            return head;
        }

        // 3. 计算从尾部开始进行反转的节点数
        int reverseStart = length % k;  // 确定从哪里开始反转
        ListNode current = head;
        ListNode prev = null;

        // 4. 跳过不需要反转的前部分（reverseStart 个节点）
        for (int i = 0; i < reverseStart; i++) {
            prev = current;
            current = current.next;
        }

        // 5. 从当前位置开始，每k个节点进行翻转
        ListNode newHead = prev == null ? head : prev;  // 如果从头开始翻转，head是新头
        ListNode tail = current;
        while (current != null) {
            ListNode segmentTail = current;
            ListNode prevSegment = null;

            // 翻转当前k个节点
            for (int i = 0; i < k && current != null; i++) {
                ListNode nextNode = current.next;
                current.next = prevSegment;
                prevSegment = current;
                current = nextNode;
            }

            // 连接翻转的部分
            if (prev == null) {
                head = prevSegment;
            } else {
                prev.next = prevSegment;
            }
            tail.next = current;

            // 更新前部分
            prev = tail;
            tail = current;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = createList(); // 创建链表
        int k = 3;  // 需要反转的每段长度
        ListNode result = reverseKFromEnd(head, k);
        printList(result); // 打印反转后的链表
    }

    // 创建链表
    public static ListNode createList() {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int i = 1; i <= 7; i++) {
            curr.next = new ListNode(i);
            curr = curr.next;
        }
        return head;
    }

    // 打印链表
    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) {
                System.out.print("->");
            }
            curr = curr.next;
        }
        System.out.println();
    }
}
