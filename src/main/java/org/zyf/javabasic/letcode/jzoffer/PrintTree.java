package org.zyf.javabasic.letcode.jzoffer;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yanfengzhang
 * @description 请实现一个函数按照层次打印二叉树，即从根节点开始，逐层从左往右打印节点值。
 * @date 2023/6/8  23:46
 */
public class PrintTree {
    /**
     * 这是一个典型的二叉树层次遍历问题，可以使用队列辅助实现。
     * 1.	首先将根节点入队。
     * 2.	当队列不为空时，循环执行以下操作：
     * •	弹出队列的首个节点，并打印该节点的值。
     * •	将该节点的左右子节点（如果存在）依次入队。
     * 3.	重复步骤2，直到队列为空。
     * <p>
     * 通过这种方式，可以按照层次打印二叉树的节点值。
     */
    public ArrayList<ArrayList<Integer>> printTree(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(level);
        }

        return result;
    }

    public static void main(String[] args) {
        PrintTree solution = new PrintTree();

        // 构建二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        ArrayList<ArrayList<Integer>> result = solution.printTree(root);
        System.out.println(result);
    }

}
