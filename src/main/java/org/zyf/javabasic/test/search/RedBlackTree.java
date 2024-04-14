package org.zyf.javabasic.test.search;

/**
 * @program: zyfboot-javabasic
 * @description: 用于表示红黑树及其基本操作，包括插入、删除和查找
 * @author: zhangyanfeng
 * @create: 2024-04-14 12:28
 **/
public class RedBlackTree {

    private TreeNode root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    // 获取节点的颜色
    private boolean color(TreeNode node) {
        return node == null ? BLACK : node.isRed;
    }

    // 左旋转
    private TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
        return y;
    }

    // 右旋转
    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == null) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }
        x.right = y;
        y.parent = x;
        return x;
    }

    // 插入节点
    public void insert(int val) {
        TreeNode newNode = new TreeNode(val);
        root = insertNode(root, newNode);
        newNode.isRed = RED; // 新插入的节点为红色
        fixInsertViolation(newNode);
    }

    // 辅助函数：插入节点
    private TreeNode insertNode(TreeNode root, TreeNode newNode) {
        if (root == null) {
            return newNode;
        }

        if (newNode.val < root.val) {
            root.left = insertNode(root.left, newNode);
            root.left.parent = root;
        } else if (newNode.val > root.val) {
            root.right = insertNode(root.right, newNode);
            root.right.parent = root;
        }

        return root;
    }

    // 插入修复违反红黑树性质的情况
    private void fixInsertViolation(TreeNode node) {
        while (node != root && color(node.parent) == RED) {
            if (node.parent == node.parent.parent.left) {
                TreeNode uncle = node.parent.parent.right;
                if (color(uncle) == RED) {
                    // Case 1: 叔叔节点是红色
                    node.parent.isRed = BLACK;
                    uncle.isRed = BLACK;
                    node.parent.parent.isRed = RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        // Case 2: 叔叔节点是黑色，且当前节点是右子节点
                        node = node.parent;
                        leftRotate(node);
                    }
                    // Case 3: 叔叔节点是黑色，且当前节点是左子节点
                    node.parent.isRed = BLACK;
                    node.parent.parent.isRed = RED;
                    rightRotate(node.parent.parent);
                }
            } else {
                TreeNode uncle = node.parent.parent.left;
                if (color(uncle) == RED) {
                    // Case 1: 叔叔节点是红色
                    node.parent.isRed = BLACK;
                    uncle.isRed = BLACK;
                    node.parent.parent.isRed = RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        // Case 2: 叔叔节点是黑色，且当前节点是左子节点
                        node = node.parent;
                        rightRotate(node);
                    }
                    // Case 3: 叔叔节点是黑色，且当前节点是右子节点
                    node.parent.isRed = BLACK;
                    node.parent.parent.isRed = RED;
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.isRed = BLACK;
    }

    // 中序遍历打印树
    public void inOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.val + " ");
            inOrderTraversal(node.right);
        }
    }

    // 查找节点
    public boolean search(int val) {
        return searchNode(root, val);
    }

    // 辅助函数：查找节点
    private boolean searchNode(TreeNode node, int val) {
        if (node == null) {
            return false;
        }

        if (val == node.val) {
            return true;
        } else if (val < node.val) {
            return searchNode(node.left, val);
        } else {
            return searchNode(node.right, val);
        }
    }


    // 红黑树节点
    class TreeNode {
        int val;
        boolean isRed; // true 表示红色，false 表示黑色
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        // 构造函数
        public TreeNode(int val) {
            this.val = val;
            this.isRed = true; // 新插入的节点默认为红色
            left = null;
            right = null;
            parent = null;
        }
    }

    public static void main(String[] args) {
        RedBlackTree rbTree = new RedBlackTree();

        // 插入节点
        rbTree.insert(10);
        rbTree.insert(20);
        rbTree.insert(30);
        rbTree.insert(40);
        rbTree.insert(50);
        rbTree.insert(25);

        // 中序遍历打印树
        System.out.println("红黑树中序遍历结果：");
        rbTree.inOrderTraversal(rbTree.root);

        // 查找节点
        int target = 30;
        if (rbTree.search(target)) {
            System.out.println("\n节点 " + target + " 存在于红黑树中。");
        } else {
            System.out.println("\n节点 " + target + " 不存在于红黑树中。");
        }
    }
}
