package org.zyf.javabasic.letcode.list.base;

/**
 * @author yanfengzhang
 * @description 节点结构
 * @date 2022/10/17  23:18
 */
public class ListNode {
    /**
     * 该节点存储的值
     */
    public int val;
    /**
     * 下一个节点的引用
     */
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    /**
     * 创建一个链表
     */
    public static ListNode createList(int[] nums) {
        ListNode head = null;
        ListNode tail = null;
        for (int num : nums) {
            ListNode node = new ListNode(num);
            if (head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }
        return head;
    }

    /**
     * 遍历一个链表
     */
    public static void traverseList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    /**
     * 在链表头部插入一个节点
     */
    public static ListNode insertAtHead(ListNode head, int val) {
        ListNode node = new ListNode(val);
        node.next = head;
        head = node;
        return head;
    }

    /**
     * 在链表头部删除一个节点
     */
    public static ListNode deleteAtHead(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode next = head.next;
        head.next = null;
        head = next;
        return head;
    }

    /**
     * 在链表尾部插入一个节点
     */
    public static ListNode insertAtTail(ListNode head, int val) {
        ListNode node = new ListNode(val);
        if (head == null) {
            return node;
        }
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        return head;
    }

    /**
     * 在链表尾部删除一个节点
     */
    public static ListNode deleteAtTail(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode prev = head;
        ListNode curr = head.next;
        while (curr.next != null) {
            prev = curr;
            curr = curr.next;
        }
        prev.next = null;
        return head;
    }

    /**
     * 在链表中间插入一个节点
     */
    public static ListNode insertInMiddle(ListNode head, int val, int position) {
        ListNode node = new ListNode(val);
        if (position == 1) {
            node.next = head;
            return node;
        }
        ListNode prev = head;
        for (int i = 1; i < position - 1 && prev != null; i++) {
            prev = prev.next;
        }
        if (prev == null) {
            return head;
        }
        ListNode next = prev.next;
        prev.next = node;
        node.next = next;
        return head;
    }

    /**
     * 在链表中间删除一个节点
     */
    public static ListNode deleteInMiddle(ListNode head, int position) {
        if (head == null) {
            return null;
        }
        if (position == 1) {
            return head.next;
        }
        ListNode prev = head;
        for (int i = 1; i < position - 1 && prev != null; i++) {
            prev = prev.next;
        }
        if (prev == null || prev.next == null) {
            return head;
        }
        ListNode curr = prev.next;
        prev.next = curr.next;
        curr.next = null;
        return head;
    }



}
