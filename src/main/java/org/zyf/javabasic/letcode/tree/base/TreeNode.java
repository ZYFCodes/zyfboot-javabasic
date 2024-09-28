package org.zyf.javabasic.letcode.tree.base;

/**
 * @author yanfengzhang
 * @description TreeNode 类代表二叉树的节点，
 * 其中 val 表示节点的值，
 * left 和 right 分别表示左子节点和右子节点。
 * @date 2023/4/10  23:21
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode() {
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

