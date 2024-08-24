package org.zyf.javabasic.letcode.featured75.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @program: zyfboot-javabasic
 * @description: 统计二叉树中好节点的数目
 * @author: zhangyanfeng
 * @create: 2024-08-24 10:13
 **/
public class GoodNodes {
    // 计数好节点的变量
    private int goodNodeCount = 0;

    // 主函数，调用递归函数进行遍历
    public int goodNodes(TreeNode root) {
        // 从根节点开始遍历，初始最大值为负无穷
        dfs(root, Integer.MIN_VALUE);
        return goodNodeCount;
    }

    // 深度优先搜索递归函数
    private void dfs(TreeNode node, int maxValue) {
        if (node == null) {
            return;
        }

        // 如果当前节点值大于或等于路径上的最大值，更新计数器
        if (node.val >= maxValue) {
            goodNodeCount++;
        }

        // 更新当前路径的最大值
        maxValue = Math.max(maxValue, node.val);

        // 递归遍历左子树和右子树
        dfs(node.left, maxValue);
        dfs(node.right, maxValue);
    }

    public static void main(String[] args) {
        // 示例 1
        TreeNode root1 = new TreeNode(3,
                new TreeNode(1),
                new TreeNode(4,
                        new TreeNode(1),
                        new TreeNode(5))
        );
        GoodNodes solution = new GoodNodes();
        System.out.println(solution.goodNodes(root1)); // 输出应为 4

        // 示例 2
        TreeNode root2 = new TreeNode(3,
                new TreeNode(3,
                        new TreeNode(4),
                        null),
                new TreeNode(2)
        );
        System.out.println(solution.goodNodes(root2)); // 输出应为 3

        // 示例 3
        TreeNode root3 = new TreeNode(1);
        System.out.println(solution.goodNodes(root3)); // 输出应为 1
    }
}
