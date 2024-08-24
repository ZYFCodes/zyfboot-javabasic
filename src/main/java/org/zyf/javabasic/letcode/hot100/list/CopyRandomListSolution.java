package org.zyf.javabasic.letcode.hot100.list;

/**
 * @program: zyfboot-javabasic
 * @description: 随机链表的复制（中等）  ​
 * @author: zhangyanfeng
 * @create: 2024-08-22 10:20
 **/
public class CopyRandomListSolution {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // Step 1: 在每个节点后面创建一个新的节点，并插入链表
        Node curr = head;
        while (curr != null) {
            Node newNode = new Node(curr.val);
            newNode.next = curr.next;
            curr.next = newNode;
            curr = newNode.next;
        }

        // Step 2: 复制 random 指针
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // Step 3: 分离两个链表
        curr = head;
        Node newHead = head.next;
        Node copy = newHead;

        while (curr != null) {
            curr.next = curr.next.next;
            if (copy.next != null) {
                copy.next = copy.next.next;
            }
            curr = curr.next;
            copy = copy.next;
        }

        return newHead;
    }

    public static void main(String[] args) {
        CopyRandomListSolution solution = new CopyRandomListSolution();

        // 创建测试用例链表 [[7,null],[13,0],[11,4],[10,2],[1,0]]
        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;

        Node result = solution.copyRandomList(node1);

        // 打印结果链表
        Node current = result;
        while (current != null) {
            System.out.print("[" + current.val + ", ");
            if (current.random != null) {
                System.out.print(current.random.val);
            } else {
                System.out.print("null");
            }
            System.out.print("] ");
            current = current.next;
        }
    }
}
