package org.zyf.javabasic.letcode.hot100.list;

import org.zyf.javabasic.letcode.list.base.ListNode;

import java.util.PriorityQueue;

/**
 * @program: zyfboot-javabasic
 * @description: 合并 K 个升序链表 （困难）
 * @author: zhangyanfeng
 * @create: 2024-08-22 10:32
 **/
public class MergeKListsSolution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 创建优先队列，并指定排序规则为节点值的升序
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 将每个链表的头节点加入优先队列
        for (ListNode list : lists) {
            if (list != null) {
                pq.offer(list);
            }
        }

        // 创建一个哨兵节点，方便操作
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // 逐步从优先队列中取出最小节点，加入结果链表
        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            current.next = minNode;
            current = current.next;

            // 如果最小节点有下一个节点，将其加入优先队列
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        MergeKListsSolution solution = new MergeKListsSolution();

        // 示例 1
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] lists = {l1, l2, l3};
        ListNode mergedList = solution.mergeKLists(lists);
        printList(mergedList); // 输出：[1, 1, 2, 3, 4, 4, 5, 6]

        // 示例 2
        ListNode[] emptyLists = {};
        ListNode mergedList2 = solution.mergeKLists(emptyLists);
        printList(mergedList2); // 输出：[]

        // 示例 3
        ListNode[] singleEmptyList = {null};
        ListNode mergedList3 = solution.mergeKLists(singleEmptyList);
        printList(mergedList3); // 输出：[]
    }

    // 打印链表
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
