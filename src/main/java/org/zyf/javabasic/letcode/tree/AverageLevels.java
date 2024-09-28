package org.zyf.javabasic.letcode.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yanfengzhang
 * @description 给定一个非空二叉树的根节点root,
 * 以数组的形式返回每一层节点的平均值。与实际答案相差10-5以内的答案可以被接受。
 * @date 2023/4/12  23:39
 */
public class AverageLevels {

    /**
     * 使用广度优先搜索（BFS）遍历二叉树，对于每一层节点，计算它们的平均值并将其加入结果数组中。
     * <p>
     * 具体实现方式：
     * 首先，定义一个队列，将根节点入队。
     * 然后，每次遍历队列中的元素，并将它们的子节点入队。
     * 对于每一层的节点，统计它们的平均值，并将其加入结果数组中。
     * 最后，返回结果数组。
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            /*当前层的节点数*/
            int levelSize = queue.size();
            /*当前层节点值的和*/
            double levelSum = 0;
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelSum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            double levelAvg = levelSum / levelSize;
            result.add(levelAvg);
        }
        return result;
    }


    public static void main(String[] args) {
        /*构造二叉树*/
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;

        /*计算每层节点值的平均数*/
        List<Double> result = new AverageLevels().averageOfLevels(root);

        /*输出结果*/
        /*预期输出 [3.0, 14.5, 11.0]*/
        System.out.println(result);
    }

}
