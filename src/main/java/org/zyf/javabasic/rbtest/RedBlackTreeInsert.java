package org.zyf.javabasic.rbtest;

/**
 * @program: zyfboot-javabasic
 * @description:
 * @author: zhangyanfeng
 * @create: 2023-08-19 12:58
 **/
public class RedBlackTreeInsert {

    private TreeNode root;

    enum Color {
        RED, BLACK
    }

    class TreeNode {
        int key;
        Color color;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        public TreeNode(int key, Color color) {
            this.key = key;
            this.color = color;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    public void insert(int key) {
        TreeNode newNode = new TreeNode(key, Color.RED);
        if (root == null) {
            root = newNode;
        } else {
            TreeNode parent = findParentForInsert(root, key);
            if (key < parent.key) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
            newNode.parent = parent;
            insertFixup(newNode);
        }
    }

    private TreeNode findParentForInsert(TreeNode node, int key) {
        TreeNode parent = null;
        while (node != null) {
            parent = node;
            if (key < node.key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return parent;
    }

    private void insertFixup(TreeNode node) {
        while (node.parent != null && node.parent.color == Color.RED) {
            if (node.parent == node.parent.parent.left) {
                TreeNode uncle = node.parent.parent.right;
                if (uncle != null && uncle.color == Color.RED) {
                    node.parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    rightRotate(node.parent.parent);
                }
            } else {
                // 同上，但是左右对调
            }
        }
        root.color = Color.BLACK;
    }

    private void leftRotate(TreeNode x) {
        // 左旋操作的代码
    }

    private void rightRotate(TreeNode y) {
        // 右旋操作的代码
    }

}
