package org.zyf.javabasic.letcode.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @author yanfengzhang
 * @description 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 高度平衡的二叉树定义为：二叉树的每个节点的左右两个子树的高度差不超过1。
 * @date 2023/4/13  00:13
 */
public class BalancedTree {

    /**
     * 一种常用的最优解法是通过递归进行深度优先搜索（DFS）。具体思路如下：
     * <p>
     * 对于每个节点，分别计算其左右子树的高度。
     * 判断当前节点的左右子树高度差是否超过1，如果超过1，则返回 false，表示不是平衡二叉树。
     * 如果当前节点的左右子树高度差没有超过1，则继续递归判断其左右子树是否是平衡二叉树。
     * 递归的终止条件是当节点为空时，返回 true。
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            /*空树视为平衡二叉树*/
            return true;
        }

        /*判断左子树和右子树的高度差是否超过1，如果超过1则不是平衡二叉树*/
        if (Math.abs(height(root.left) - height(root.right)) > 1) {
            return false;
        }

        /*判断左子树和右子树是否都是平衡二叉树*/
        return isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 计算二叉树的高度
     */
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        /*递归计算左子树和右子树的最大高度，并加上当前节点的高度1*/
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public static void main(String[] args) {
        /*构建一个二叉树*/
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;

        /*判断根节点是否为平衡二叉树*/
        boolean isBalanced = new BalancedTree().isBalanced(root);

        /*输出结果*/
        if (isBalanced) {
            System.out.println("该二叉树是平衡二叉树。");
        } else {
            System.out.println("该二叉树不是平衡二叉树。");
        }
    }

}
