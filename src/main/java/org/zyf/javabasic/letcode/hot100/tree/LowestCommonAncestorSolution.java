package org.zyf.javabasic.letcode.hot100.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @program: zyfboot-javabasic
 * @description: 二叉树的最近公共祖先（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 12:18
 **/
public class LowestCommonAncestorSolution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 递归终止条件：如果当前节点为空或等于 p 或 q，直接返回当前节点
        if (root == null || root == p || root == q) {
            return root;
        }

        // 递归查找左子树
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 递归查找右子树
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 如果左子树和右子树都找到了 p 或 q，那么当前节点是 LCA
        if (left != null && right != null) {
            return root;
        }

        // 如果左子树找到了 p 或 q，则返回左子树的结果，否则返回右子树的结果
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        // 构造测试用例1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(1);
        root1.left.left = new TreeNode(6);
        root1.left.right = new TreeNode(2);
        root1.right.left = new TreeNode(0);
        root1.right.right = new TreeNode(8);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(4);

        LowestCommonAncestorSolution solution = new LowestCommonAncestorSolution();
        TreeNode p1 = root1.left; // Node 5
        TreeNode q1 = root1.right; // Node 1
        TreeNode result1 = solution.lowestCommonAncestor(root1, p1, q1);
        System.out.println("Test Case 1 Result: " + result1.val); // Expected output: 3

        // 构造测试用例2
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(5);
        root2.right = new TreeNode(1);
        root2.left.left = new TreeNode(6);
        root2.left.right = new TreeNode(2);
        root2.right.left = new TreeNode(0);
        root2.right.right = new TreeNode(8);
        root2.left.right.left = new TreeNode(7);
        root2.left.right.right = new TreeNode(4);

        TreeNode p2 = root2.left; // Node 5
        TreeNode q2 = root2.left.right.right; // Node 4
        TreeNode result2 = solution.lowestCommonAncestor(root2, p2, q2);
        System.out.println("Test Case 2 Result: " + result2.val); // Expected output: 5

        // 构造测试用例3
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);

        LowestCommonAncestorSolution solution3 = new LowestCommonAncestorSolution();
        TreeNode p3 = root3; // Node 1
        TreeNode q3 = root3.left; // Node 2
        TreeNode result3 = solution3.lowestCommonAncestor(root3, p3, q3);
        System.out.println("Test Case 3 Result: " + result3.val); // Expected output: 1
    }
}
