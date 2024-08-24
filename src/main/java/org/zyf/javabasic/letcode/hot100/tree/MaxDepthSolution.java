package org.zyf.javabasic.letcode.hot100.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @program: zyfboot-javabasic
 * @description: 二叉树的最大深度（简单）
 * @author: zhangyanfeng
 * @create: 2024-08-22 10:51
 **/
public class MaxDepthSolution {
    // 递归计算二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 计算左右子树的深度
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        // 返回较大深度加上根节点本身
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // 测试主函数
    public static void main(String[] args) {
        // 构造测试用例
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);

        // 创建 Solution 实例并进行测试
        MaxDepthSolution solution = new MaxDepthSolution();
        int depth1 = solution.maxDepth(root1);
        int depth2 = solution.maxDepth(root2);

        // 打印结果
        System.out.println(depth1);  // 输出应为 3
        System.out.println(depth2);  // 输出应为 2
    }
}
