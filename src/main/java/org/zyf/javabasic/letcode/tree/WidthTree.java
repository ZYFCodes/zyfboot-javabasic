package org.zyf.javabasic.letcode.tree;

import javafx.util.Pair;
import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/4/13  00:03
 */
public class WidthTree {

    /**
     * 最优解法的思路是基于层序遍历的，
     * 需要使用一个队列来存储每一层的节点，
     * 同时记录每个节点在该层的位置（可以用编号或者下标来表示），
     * 然后计算每一层的宽度，并找到最大的宽度即可。
     * <p>
     * 具体来说，使用一个队列来进行层序遍历，
     * 每次遍历一层时，将该层节点加入队列中，并且记录下每个节点的位置，
     * 初始时根节点的位置为0，左儿子节点的位置为当前节点的位置乘2，
     * 右儿子节点的位置为当前节点的位置乘2加1。
     * 然后计算该层节点的宽度，
     * 即最后一个节点的位置减去第一个节点的位置再加1，更新最大宽度即可。
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxWidth = 0;
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        /*将根节点入队，根节点的位置为 1*/
        queue.offer(new Pair<>(root, 1));
        while (!queue.isEmpty()) {
            int size = queue.size();
            /*记录本层最左侧节点的位置*/
            int left = queue.peek().getValue();
            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Integer> pair = queue.poll();
                int pos = pair.getValue();
                /*更新最大宽度*/
                maxWidth = Math.max(maxWidth, pos - left + 1);
                TreeNode node = pair.getKey();
                if (node.left != null) {
                    /*左子节点位置为父节点位置的 2 倍*/
                    queue.offer(new Pair<>(node.left, pos * 2));
                }
                if (node.right != null) {
                    /*右子节点位置为父节点位置的 2 倍加 1*/
                    queue.offer(new Pair<>(node.right, pos * 2 + 1));
                }
            }
        }
        return maxWidth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);

        int maxWidth = new WidthTree().widthOfBinaryTree(root);
        /*输出 4*/
        System.out.println(maxWidth);
    }
}
