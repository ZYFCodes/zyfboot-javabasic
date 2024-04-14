package org.zyf.javabasic.test.search;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @program: zyfboot-javabasic
 * @description: 用于表示二叉搜索树（BST）及其基本操作，包括插入和查找
 * @author: zhangyanfeng
 * @create: 2024-04-14 12:16
 **/
public class BinarySearchTree {
    TreeNode root;

    // 构造函数
    public BinarySearchTree() {
        root = null;
    }

    // 插入节点
    public void insert(int val) {
        root = insertNode(root, val);
    }

    // 辅助函数：插入节点
    private TreeNode insertNode(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }

        if (val < node.val) {
            node.left = insertNode(node.left, val);
        } else if (val > node.val) {
            node.right = insertNode(node.right, val);
        }

        return node;
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

    public static void main(String[] args) {
        // 创建一个二叉搜索树
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);
        bst.insert(12);
        bst.insert(20);

        // 查找节点
        int target = 7;
        if (bst.search(target)) {
            System.out.println("节点 " + target + " 存在于二叉搜索树中。");
        } else {
            System.out.println("节点 " + target + " 不存在于二叉搜索树中。");
        }
    }
}
