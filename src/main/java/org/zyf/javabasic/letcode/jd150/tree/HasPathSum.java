package org.zyf.javabasic.letcode.jd150.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @program: zyfboot-javabasic
 * @description: 路径总和
 * @author: zhangyanfeng
 * @create: 2024-08-25 14:07
 **/
public class HasPathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false; // 如果当前节点为空，直接返回 false
        }

        // 如果当前节点是叶子节点，判断路径和是否等于 targetSum
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        // 递归检查左右子树是否存在符合条件的路径
        int remainingSum = targetSum - root.val;
        return hasPathSum(root.left, remainingSum) || hasPathSum(root.right, remainingSum);
    }

    public static void main(String[] args) {
        HasPathSum sol = new HasPathSum();

        // 示例 1 测试
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(11);
        root1.left.left.left = new TreeNode(7);
        root1.left.left.right = new TreeNode(2);
        root1.right.left = new TreeNode(13);
        root1.right.right = new TreeNode(4);
        root1.right.right.right = new TreeNode(1);

        System.out.println(sol.hasPathSum(root1, 22)); // 输出: true

        // 示例 2 测试
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);

        System.out.println(sol.hasPathSum(root2, 5)); // 输出: false

        // 示例 3 测试
        TreeNode root3 = null;

        System.out.println(sol.hasPathSum(root3, 0)); // 输出: false
    }
}
