package org.zyf.javabasic.rbtest;

/**
 * @program: zyfboot-javabasic
 * @description:
 * @author: zhangyanfeng
 * @create: 2023-08-19 12:38
 **/
public class RedBlackTree {

    private TreeNode root;

    public RedBlackTree() {
        root = null;
    }

    class TreeNode {
        int key;
        boolean isRed; // true for red, false for black
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        public TreeNode(int key, boolean isRed) {
            this.key = key;
            this.isRed = isRed;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    // 左旋操作
    private void leftRotate(TreeNode x) {
        TreeNode y = x.right; // y 为 x 的右子节点
        x.right = y.left; // 将 y 的左子节点设置为 x 的右子节点
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent; // 更新 y 的父节点
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x; // 将 x 设为 y 的左子节点
        x.parent = y;
    }

    // 右旋操作
    private void rightRotate(TreeNode y) {
        TreeNode x = y.left; // x 为 y 的左子节点
        y.left = x.right; // 将 x 的右子节点设置为 y 的左子节点
        if (x.right != null) {
            x.right.parent = y;
        }
        x.parent = y.parent; // 更新 x 的父节点
        if (y.parent == null) {
            root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        x.right = y; // 将 y 设为 x 的右子节点
        y.parent = x;
    }

}
