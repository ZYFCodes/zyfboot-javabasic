package org.zyf.javabasic.letcode.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @author yanfengzhang
 * @description 给定一个二叉树，其中每个节点都包含一个 0 或 1 的值，每条从根到叶子节点的路径都代表一个二进制数。求所有路径代表的二进制数之和。
 * 例如，给定以下二叉树:
 * 1
 * / \
 * 2   3
 * 从根到叶子节点的路径包括 1->2 和 1->3，所以二进制数之和为 12 + 13 = 25。
 * 以下是修正后的题目描述：
 * 给定一个二叉树，其中每个节点都包含一个 0 或 1 的值，每条从根到叶子节点的路径都代表一个二进制数。求所有路径代表的二进制数之和。
 * 请编写一个函数来计算二叉树中所有路径代表的二进制数之和。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
 * @date 2023/4/12  22:56
 */
public class SumRootToLeafNumbers {
    int sum = 0;

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root, 0);
        return sum;
    }

    private void dfs(TreeNode node, int pathValue) {
        /*更新路径值*/
        pathValue = pathValue * 10 + node.val;

        /*到达叶子节点，累加到二进制数之和*/
        if (node.left == null && node.right == null) {
            sum += pathValue;
            return;
        }

        /*递归遍历左子树和右子树*/
        if (node.left != null) {
            dfs(node.left, pathValue);
        }
        if (node.right != null) {
            dfs(node.right, pathValue);
        }
    }

    public static void main(String[] args) {
        // 创建二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        // 计算二叉树中所有路径代表的二进制数之和
        int sum = new SumRootToLeafNumbers().sumNumbers(root);

        // 输出结果
        System.out.println("Sum of root-to-leaf numbers: " + sum);
    }

}
