package org.zyf.javabasic.letcode.hot100.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 路径总和 III（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 12:12
 **/
public class PathSumSolution {
    public int pathSum(TreeNode root, int targetSum) {
        // 哈希表存储前缀和及其出现次数
        Map<Long, Integer> prefix = new HashMap<>();
        // 初始前缀和为0，出现次数为1
        prefix.put(0L, 1);
        // 进行深度优先搜索
        return dfs(root, prefix, 0, targetSum);
    }

    private int dfs(TreeNode node, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (node == null) {
            return 0;
        }

        int result = 0;
        // 更新当前路径和
        curr += node.val;

        // 当前路径和减去目标和的前缀和出现次数
        result = prefix.getOrDefault(curr - targetSum, 0);

        // 更新前缀和出现次数
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);

        // 递归访问左子树和右子树
        result += dfs(node.left, prefix, curr, targetSum);
        result += dfs(node.right, prefix, curr, targetSum);

        // 恢复哈希表状态
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return result;
    }

    public static void main(String[] args) {
        // 构造测试用例1
        TreeNode root1 = new TreeNode(10);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(-3);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(2);
        root1.right.right = new TreeNode(11);
        root1.left.left.left = new TreeNode(3);
        root1.left.left.right = new TreeNode(-2);
        root1.left.right.right = new TreeNode(1);

        PathSumSolution solution = new PathSumSolution();
        int result1 = solution.pathSum(root1, 8);
        System.out.println("Test Case 1 Result: " + result1);  // Expected output: 3

        // 构造测试用例2
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(8);
        root2.left.left = new TreeNode(11);
        root2.right.left = new TreeNode(13);
        root2.right.right = new TreeNode(4);
        root2.left.left.left = new TreeNode(7);
        root2.left.left.right = new TreeNode(2);
        root2.right.right.right = new TreeNode(1);

        int result2 = solution.pathSum(root2, 22);
        System.out.println("Test Case 2 Result: " + result2);  // Expected output: 3
    }

}
