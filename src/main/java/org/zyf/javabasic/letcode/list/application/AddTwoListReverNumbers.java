package org.zyf.javabasic.letcode.list.application;

/**
 * @program: zyfboot-javabasic
 * @description: 链表相加（反方向右对齐方式）
 * @author: zhangyanfeng
 * @create: 2024-09-03 12:00
 **/
public class AddTwoListReverNumbers {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    // 计算链表长度
    private int getLength(ListNode node) {
        int length = 0;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }

    // 辅助递归函数：在链表长度对齐之后进行相加
    private ListNode addLists(ListNode l1, ListNode l2, int[] carry) {
        if (l1 == null && l2 == null) {
            return null;
        }

        // 递归调用
        ListNode nextNode = addLists(l1.next, l2.next, carry);

        // 当前位的计算
        int sum = l1.val + l2.val + carry[0];
        carry[0] = sum / 10; // 计算进位

        // 构造当前节点
        ListNode currentNode = new ListNode(sum % 10);
        currentNode.next = nextNode;

        return currentNode;
    }

    // 主函数：处理链表长度对齐，并调用递归函数
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int len1 = getLength(l1);
        int len2 = getLength(l2);

        // 将较短的链表前面补零，使得两个链表长度相等
        while (len1 < len2) {
            l1 = new ListNode(0, l1);
            len1++;
        }
        while (len2 < len1) {
            l2 = new ListNode(0, l2);
            len2++;
        }

        int[] carry = new int[1];
        ListNode result = addLists(l1, l2, carry);

        // 如果最高位有进位，需要在前面增加一个新节点
        if (carry[0] != 0) {
            result = new ListNode(carry[0], result);
        }

        return result;
    }

    public static void main(String[] args) {
        AddTwoListReverNumbers solution = new AddTwoListReverNumbers();

        // 创建链表1: 7 -> 2 -> 4 -> 3
        ListNode l1 = new ListNode(7, new ListNode(2, new ListNode(4, new ListNode(3))));

        // 创建链表2: 5 -> 6 -> 4
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        // 计算和
        ListNode result = solution.addTwoNumbers(l1, l2);

        // 打印结果
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
