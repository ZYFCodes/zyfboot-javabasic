package org.zyf.javabasic.letcode.featured75.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: zyfboot-javabasic
 * @description: 最大层内元素和
 * @author: zhangyanfeng
 * @create: 2024-08-24 10:58
 **/
public class MaxLevelSum {
    public int maxLevelSum(TreeNode root) {
        if (root == null) return 0;

        // 队列用于层次遍历，存储每一层的节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int level = 0;
        int maxSum = Integer.MIN_VALUE; // 最大和初始化为负无穷
        int minLevel = 0; // 记录最大和的最小层号

        while (!queue.isEmpty()) {
            level++;
            int levelSize = queue.size();
            int levelSum = 0;

            // 计算当前层的节点和
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelSum += node.val;

                // 添加下一层的节点到队列
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            // 更新最大和及其对应的层号
            if (levelSum > maxSum) {
                maxSum = levelSum;
                minLevel = level;
            }
        }

        return minLevel;
    }

    // 主函数用于测试
    public static void main(String[] args) {
        MaxLevelSum solution = new MaxLevelSum();

        // 示例 1
        TreeNode root1 = new TreeNode(1,
                new TreeNode(7,
                        new TreeNode(7),
                        new TreeNode(-8)
                ),
                new TreeNode(0)
        );
        System.out.println(solution.maxLevelSum(root1)); // 输出: 2

        // 示例 2
        TreeNode root2 = new TreeNode(989,
                null,
                new TreeNode(10250,
                        new TreeNode(98693),
                        new TreeNode(-89388)
                )
        );
        System.out.println(solution.maxLevelSum(root2)); // 输出: 2

        // 示例 3
        TreeNode root3 = new TreeNode(1);
        System.out.println(solution.maxLevelSum(root3)); // 输出: 1

        // 自定义测试用例
        TreeNode root4 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)
                ),
                new TreeNode(3,
                        new TreeNode(6),
                        null
                )
        );
        System.out.println(solution.maxLevelSum(root4)); // 输出: 2 (层次和分别为 1, 5, 11)

        TreeNode root5 = new TreeNode(10,
                new TreeNode(-5,
                        new TreeNode(1),
                        new TreeNode(2)
                ),
                new TreeNode(15,
                        null,
                        new TreeNode(8)
                )
        );
        System.out.println(solution.maxLevelSum(root5)); // 输出: 2 (层次和分别为 10, 10, 11)
    }
}
