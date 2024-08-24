package org.zyf.javabasic.letcode.hot100.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: zyfboot-javabasic
 * @description: 对称二叉树（简单）
 * @author: zhangyanfeng
 * @create: 2024-08-22 11:15
 **/
public class IsSymmetricSolution {

    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return (t1.val == t2.val) &&
                isMirror(t1.right, t2.left) &&
                isMirror(t1.left, t2.right);
    }

    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();

            if (t1 == null && t2 == null) {
                continue;
            }
            if (t1 == null || t2 == null) {
                return false;
            }
            if (t1.val != t2.val) {
                return false;
            }

            queue.offer(t1.left);
            queue.offer(t2.right);
            queue.offer(t1.right);
            queue.offer(t2.left);
        }

        return true;
    }

    public static void main(String[] args) {
        IsSymmetricSolution solution = new IsSymmetricSolution();

        // 示例 1: 对称的二叉树
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(3);

        System.out.println("Example 1: " + solution.isSymmetric1(root1)); // 输出: true

        // 示例 2: 不对称的二叉树
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(3);

        System.out.println("Example 2: " + solution.isSymmetric2(root2)); // 输出: false

        // 示例 3: 空树
        TreeNode root3 = null;

        System.out.println("Example 3: " + solution.isSymmetric2(root3)); // 输出: true
    }
}
