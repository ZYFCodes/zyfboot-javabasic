package org.zyf.javabasic.letcode.jd150.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.*;

/**
 * @program: zyfboot-javabasic
 * @description: 二叉树的锯齿形层序遍历
 * @author: zhangyanfeng
 * @create: 2024-08-25 14:44
 **/
public class ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // 定义结果列表
        List<List<Integer>> result = new ArrayList<>();
        // 如果树为空，直接返回空列表
        if (root == null) {
            return result;
        }

        // 初始化队列进行层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 标志位，初始为从左到右
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            // 当前层的节点数量
            int levelSize = queue.size();
            // 使用双端队列来存储当前层的节点值
            Deque<Integer> level = new LinkedList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (leftToRight) {
                    // 从左到右，将节点值添加到队列的尾部
                    level.offerLast(node.val);
                } else {
                    // 从右到左，将节点值添加到队列的头部
                    level.offerFirst(node.val);
                }
                // 将当前节点的左右子节点加入队列
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // 将当前层的结果添加到结果列表
            result.add(new LinkedList<>(level));
            // 切换遍历方向
            leftToRight = !leftToRight;
        }

        return result;
    }
}
