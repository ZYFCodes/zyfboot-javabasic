package org.zyf.javabasic.letcode.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yanfengzhang
 * @description 给定一棵二叉树，求其从右侧看所能看到的节点值序列，即每层最右边的节点。
 * @date 2023/4/12  23:59
 */
public class RightSideView {
    /**
     * 求二叉树右视图:二叉树的右视图可以通过层序遍历来实现。
     * 在层序遍历时，每层最后一个节点即为该层最右边的节点，
     * 因此只需要记录每层的最后一个节点即可。具体实现步骤如下：
     * <p>
     * 如果根节点为空，则返回空列表。
     * 初始化结果列表res和队列queue，将根节点加入队列。
     * 进入循环，每次循环表示遍历一层。先获取当前队列的长度size，将该层最后一个节点的值加入res中。然后依次弹出队列中的节点，并将其左右子节点加入队列中。
     * 循环结束后，返回结果列表res。
     *
     * @param root 二叉树根节点
     * @return 右视图节点值序列
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) {
                    /*当前层最后一个节点是从右侧能看到的节点*/
                    result.add(node.val);
                }
                if (node.left != null) {
                    /*将左子节点加入队列*/
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    /*将右子节点加入队列*/
                    queue.offer(node.right);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        /*创建一颗二叉树*/
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        root.left = node2;
        root.right = node3;
        node2.right = node5;
        node3.right = node4;
        node5.left = node6;
        node5.right = node7;

        /*测试右视图函数*/
        List<Integer> res = new RightSideView().rightSideView(root);
        System.out.println(res);
    }

}
