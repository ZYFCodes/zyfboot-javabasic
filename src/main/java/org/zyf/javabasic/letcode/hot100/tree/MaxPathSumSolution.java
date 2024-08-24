package org.zyf.javabasic.letcode.hot100.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @program: zyfboot-javabasic
 * @description: 二叉树中的最大路径和 （困难）
 * @author: zhangyanfeng
 * @create: 2024-08-22 12:23
 **/
public class MaxPathSumSolution {
    private int maxSum = Integer.MIN_VALUE; // 全局变量，存储最大路径和

    public int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return maxSum;
    }

    // 递归函数，计算从当前节点向下延伸的最大路径和
    private int maxPathSumHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归计算左子树和右子树的最大路径和
        int left = Math.max(maxPathSumHelper(node.left), 0); // 负数路径不贡献
        int right = Math.max(maxPathSumHelper(node.right), 0); // 负数路径不贡献

        // 当前节点的最大路径和为当前节点值 + 左右子树的最大路径和
        int currentSum = node.val + left + right;

        // 更新全局最大路径和
        maxSum = Math.max(maxSum, currentSum);

        // 返回当前节点的最大路径和
        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        // 构造测试用例1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);

        MaxPathSumSolution solution1 = new MaxPathSumSolution();
        int result1 = solution1.maxPathSum(root1);
        System.out.println("Test Case 1 Result: " + result1); // Expected output: 6

        // 构造测试用例2
        TreeNode root2 = new TreeNode(-10);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(7);

        MaxPathSumSolution solution2 = new MaxPathSumSolution();
        int result2 = solution2.maxPathSum(root2);
        System.out.println("Test Case 2 Result: " + result2); // Expected output: 42
    }
}
