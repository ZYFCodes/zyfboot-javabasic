package org.zyf.javabasic.test.search;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @program: zyfboot-javabasic
 * @description: 用于表示伸展树（Splay Tree）及其基本操作，包括插入、删除和查找
 * @author: zhangyanfeng
 * @create: 2024-04-14 12:31
 **/
public class SplayTree {
    private TreeNode root;

    // 旋转：左旋
    private TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    // 旋转：右旋
    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        y.left = x.right;
        x.right = y;
        return x;
    }

    // 伸展：将节点 x 伸展到根节点
    private TreeNode splay(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }

        if (val < root.val) {
            // 待查找的值在左子树中
            if (root.left == null) {
                return root;
            }
            // Zig-Zig (left left)
            if (val < root.left.val) {
                root.left.left = splay(root.left.left, val);
                root = rightRotate(root);
            }
            // Zig-Zag (left right)
            else if (val > root.left.val) {
                root.left.right = splay(root.left.right, val);
                if (root.left.right != null) {
                    root.left = leftRotate(root.left);
                }
            }
            if (root.left != null) {
                root = rightRotate(root);
            }
            return (root.left == null) ? root : rightRotate(root);
        } else {
            // 待查找的值在右子树中
            if (root.right == null) {
                return root;
            }
            // Zag-Zag (right right)
            if (val > root.right.val) {
                root.right.right = splay(root.right.right, val);
                root = leftRotate(root);
            }
            // Zag-Zig (right left)
            else if (val < root.right.val) {
                root.right.left = splay(root.right.left, val);
                if (root.right.left != null) {
                    root.right = rightRotate(root.right);
                }
            }
            if (root.right != null) {
                root = leftRotate(root);
            }
            return (root.right == null) ? root : leftRotate(root);
        }
    }

    // 插入节点
    public TreeNode insert(int val) {
        root = insertNode(root, val);
        root = splay(root, val);
        return root;
    }

    // 辅助函数：插入节点
    private TreeNode insertNode(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insertNode(root.left, val);
        } else if (val > root.val) {
            root.right = insertNode(root.right, val);
        }
        return root;
    }

    // 查找节点
    public boolean search(int val) {
        root = splay(root, val);
        return root != null && root.val == val;
    }

    public static void main(String[] args) {
        SplayTree splayTree = new SplayTree();

        // 插入节点
        splayTree.insert(10);
        splayTree.insert(20);
        splayTree.insert(30);
        splayTree.insert(40);
        splayTree.insert(50);

        // 查找节点
        int target = 30;
        if (splayTree.search(target)) {
            System.out.println("节点 " + target + " 存在于伸展树中。");
        } else {
            System.out.println("节点 " + target + " 不存在于伸展树中。");
        }
    }
}
