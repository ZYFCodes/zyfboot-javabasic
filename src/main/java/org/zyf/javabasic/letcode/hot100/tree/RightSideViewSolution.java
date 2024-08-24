package org.zyf.javabasic.letcode.hot100.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: zyfboot-javabasic
 * @description: 二叉树的右视图（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 11:53
 **/
public class RightSideViewSolution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Integer rightMostValue = null;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                rightMostValue = node.val;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(rightMostValue);
        }

        return result;
    }

    public static void main(String[] args) {
        RightSideViewSolution solution = new RightSideViewSolution();

        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(5);
        root1.right.right = new TreeNode(4);
        System.out.println(solution.rightSideView(root1));  // Output: [1, 3, 4]

        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(3);
        System.out.println(solution.rightSideView(root2));  // Output: [1, 3]

        // Example 3
        TreeNode root3 = null;
        System.out.println(solution.rightSideView(root3));  // Output: []
    }
}
