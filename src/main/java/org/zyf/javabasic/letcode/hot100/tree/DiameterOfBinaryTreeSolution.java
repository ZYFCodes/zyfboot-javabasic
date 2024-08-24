package org.zyf.javabasic.letcode.hot100.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @program: zyfboot-javabasic
 * @description: 二叉树的直径（简单）
 * @author: zhangyanfeng
 * @create: 2024-08-22 11:23
 **/
public class DiameterOfBinaryTreeSolution {
    private int maxDiameter = 0; // 记录最大直径

    public int diameterOfBinaryTree(TreeNode root) {
        calculateDepth(root);
        return maxDiameter;
    }

    // 计算树的深度并更新最大直径
    private int calculateDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归计算左右子树的深度
        int leftDepth = calculateDepth(node.left);
        int rightDepth = calculateDepth(node.right);

        // 更新最大直径
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);

        // 返回当前节点的深度
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        DiameterOfBinaryTreeSolution solution = new DiameterOfBinaryTreeSolution();

        // 示例 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);

        System.out.println("Example 1: " + solution.diameterOfBinaryTree(root1)); // 输出: 3

        // 示例 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);

        System.out.println("Example 2: " + solution.diameterOfBinaryTree(root2)); // 输出: 1
    }
}
