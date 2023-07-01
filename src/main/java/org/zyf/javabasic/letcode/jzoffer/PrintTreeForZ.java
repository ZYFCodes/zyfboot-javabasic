package org.zyf.javabasic.letcode.jzoffer;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yanfengzhang
 * @description 请实现一个函数按照之字形顺序打印二叉树，
 * 即第一行按照从左到右的顺序打印，
 * 第二行按照从右到左的顺序打印，
 * 第三行再按照从左到右的顺序打印，以此类推。
 * @date 2023/6/8  00:49
 */
public class PrintTreeForZ {
    /**
     * 该问题可以通过层次遍历二叉树并使用双端队列来实现。
     * 具体步骤如下：
     * 1.	创建一个结果列表 result 来存储最终的打印结果。
     * 2.	若二叉树根节点为空，直接返回结果列表 result。
     * 3.	创建一个双端队列 deque，用于层次遍历二叉树。
     * 4.	将根节点加入 deque。
     * 5.	创建一个布尔变量 leftToRight，初始值为 true，表示当前层的打印顺序从左到右。
     * 6.	进入循环，直到 deque 为空。
     * •	获取当前层的节点个数 size。
     * •	创建一个列表 level，用于存储当前层的节点值。
     * •	遍历当前层的节点：
     * •	若 leftToRight 为 true，从队列的头部弹出节点，并将其值加入 level。
     * •	若 leftToRight 为 false，从队列的尾部弹出节点，并将其值加入 level。
     * •	若节点有左子节点，则将左子节点加入队列的尾部。
     * •	若节点有右子节点，则将右子节点加入队列的尾部。
     * •	将 level 加入结果列表 result。
     * •	反转 leftToRight 的值，以改变下一层的打印顺序。
     * 7.	返回结果列表 result。
     */
    public ArrayList<ArrayList<Integer>> printTree(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        boolean leftToRight = true;

        while (!deque.isEmpty()) {
            int size = deque.size();
            ArrayList<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node;
                if (leftToRight) {
                    node = deque.pollFirst();
                } else {
                    node = deque.pollLast();
                }
                level.add(node.val);

                if (leftToRight) {
                    if (node.left != null) {
                        deque.offerLast(node.left);
                    }
                    if (node.right != null) {
                        deque.offerLast(node.right);
                    }
                } else {
                    if (node.right != null) {
                        deque.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        deque.offerFirst(node.left);
                    }
                }
            }

            leftToRight = !leftToRight;
            result.add(level);
        }

        return result;
    }

    public static void main(String[] args) {
        PrintTreeForZ solution = new PrintTreeForZ();

        // 构建二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        ArrayList<ArrayList<Integer>> result = solution.printTree(root);
        for (ArrayList<Integer> level : result) {
            System.out.println(level);
        }
    }
}
