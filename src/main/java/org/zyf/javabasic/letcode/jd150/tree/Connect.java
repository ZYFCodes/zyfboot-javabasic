package org.zyf.javabasic.letcode.jd150.tree;

/**
 * @program: zyfboot-javabasic
 * @description: 填充每个节点的下一个右侧节点指针 II
 * @author: zhangyanfeng
 * @create: 2024-08-25 13:57
 **/
public class Connect {
    Node last = null;        // 当前层已连接的最后一个节点
    Node nextStart = null;   // 下一层最左侧的起始节点

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node start = root;   // 从根节点开始
        while (start != null) {
            last = null;
            nextStart = null;
            for (Node p = start; p != null; p = p.next) {
                if (p.left != null) {
                    handle(p.left);  // 处理左子节点
                }
                if (p.right != null) {
                    handle(p.right); // 处理右子节点
                }
            }
            start = nextStart;  // 转向下一层的最左节点
        }
        return root;
    }

    // 处理每个节点，连接next指针
    public void handle(Node p) {
        if (last != null) {
            last.next = p;   // 将上一个节点的next指向当前节点
        }
        if (nextStart == null) {
            nextStart = p;   // 记录下一层的起始节点
        }
        last = p;  // 更新last为当前节点
    }

    public static void main(String[] args) {
        Connect sol = new Connect();

        // 示例测试
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);

        sol.connect(root);
        printLevels(root);
    }

    // 辅助函数：按层打印节点的 next 连接结果
    private static void printLevels(Node root) {
        Node levelStart = root;
        while (levelStart != null) {
            Node current = levelStart;
            while (current != null) {
                System.out.print(current.val + " ");
                current = current.next;
            }
            System.out.println("#");
            levelStart = levelStart.left;
        }
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
