package org.zyf.javabasic.letcode.hot100.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @program: zyfboot-javabasic
 * @description: 验证二叉搜索树（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 11:42
 **/
public class IsValidBSTSolution {
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBSTHelper(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }

        // Check current node value
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // Recursively check left and right subtrees
        return isValidBSTHelper(node.left, min, node.val) &&
                isValidBSTHelper(node.right, node.val, max);
    }

    public static void main(String[] args) {
        IsValidBSTSolution solution = new IsValidBSTSolution();

        // Example 1
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        System.out.println(solution.isValidBST(root1));  // Output: true

        // Example 2
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);
        System.out.println(solution.isValidBST(root2));  // Output: false
    }
}
