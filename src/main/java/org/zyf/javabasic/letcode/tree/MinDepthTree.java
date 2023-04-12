package org.zyf.javabasic.letcode.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @author yanfengzhang
 * @description 给定一棵二叉树，求其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 注意，叶子节点是指没有子节点的节点。
 * @date 2023/4/11  23:06
 */
public class MinDepthTree {

    /**
     * 二叉树的最小深度问题可以使用递归的思想来解决。
     * 类似于求最大深度，假设要求以节点 node 为根节点的二叉树的最小深度，
     * 可以先分别求出其左子树和右子树的最小深度，
     * 然后将二者的最小值加上1，即可得到以 node 为根节点的二叉树的最小深度。
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            /*当前节点为空，返回深度 0*/
            return 0;
        }
        if (root.left == null) {
            /*左子树为空，返回右子树的最小深度*/
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            /*右子树为空，返回左子树的最小深度*/
            return minDepth(root.left) + 1;
        }
        /*当前节点左右子树都不为空，返回左右子树深度的最小值加1*/
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        /*
         *      3
         *     / \
         *    9  20
         *      /  \
         *     15   7
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        /*输出 2*/
        System.out.println(new MinDepthTree().minDepth(root));
    }
}
