package org.zyf.javabasic.letcode.hot100.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @program: zyfboot-javabasic
 * @description: 二叉搜索树中第 K 小的元素（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 11:48
 **/
public class KthSmallestSolution {
    private int count = 0; // Count of nodes visited
    private int result = -1; // To store the kth smallest value

    public int kthSmallest(TreeNode root, int k) {
        // Perform in-order traversal
        inOrderTraversal(root, k);
        return result;
    }

    private void inOrderTraversal(TreeNode node, int k) {
        if (node == null) {
            return;
        }

        // Traverse left subtree
        inOrderTraversal(node.left, k);

        // Visit node
        count++;
        if (count == k) {
            result = node.val;
            return;
        }

        // Traverse right subtree
        inOrderTraversal(node.right, k);
    }

    public static void main(String[] args) {
        KthSmallestSolution solution = new KthSmallestSolution();

        // Example 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.right = new TreeNode(2);
        System.out.println(solution.kthSmallest(root1, 1));  // Output: 1

        // Example 2
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.left.left.left = new TreeNode(1);
        System.out.println(solution.kthSmallest(root2, 3));  // Output: 3
    }
}
