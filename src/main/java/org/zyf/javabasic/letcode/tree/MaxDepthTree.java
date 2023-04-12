package org.zyf.javabasic.letcode.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @author yanfengzhang
 * @description 给定一个二叉树，找出其最大深度。二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * @date 2023/4/11  00:00
 */
public class MaxDepthTree {
    /**
     * 具体实现如下：
     * <p>
     * 如果 root 为空，返回 0。
     * 否则，分别求出以 root.left 为根节点的二叉树的最大深度 leftDepth 和以 root.right 为根节点的二叉树的最大深度 rightDepth。
     * 返回 leftDepth 和 rightDepth 中的最大值加上1。
     */
    public int maxDepth(TreeNode root) {
        /*如果根节点为空，返回深度 0*/
        if (root == null) {
            return 0;
        }
        /*分别求出左子树和右子树的最大深度*/
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        /*返回左右子树中的最大深度加上1*/
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;

        int maxDepth = new MaxDepthTree().maxDepth(root);
        System.out.println("二叉树的最大深度为：" + maxDepth);
    }
}
