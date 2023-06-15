package org.zyf.javabasic.letcode.tree;


import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 给定一个二叉树和一个目标和，
 * 判断该树中是否存在根节点到叶子节点的路径，
 * 这条路径上所有节点值相加等于给定的目标和。
 * @date 2023/4/11  23:27
 */
public class PathSumOfTree {


    /**
     * 输出二叉树中从根节点到叶子节点路径上所有节点值之和等于目标值的路径
     * 我们可以使用深度优先搜索（DFS）来遍历二叉树。
     * 在遍历过程中，我们需要记录当前路径的节点值之和，并判断是否满足目标值。
     * 当遍历到叶子节点时，如果路径的节点值之和等于目标值，则将该路径添加到结果列表中。
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        /*用于存储结果的列表*/
        List<List<Integer>> result = new ArrayList<>();
        /*用于存储当前路径的节点值*/
        List<Integer> path = new ArrayList<>();

        dfs(root, target, path, result);

        return result;
    }

    private void dfs(TreeNode node, int target, List<Integer> path, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        /*将当前节点的值添加到路径中*/
        path.add(node.val);

        /*到达叶子节点并且路径和等于目标值时，将该路径加入结果列表*/
        if (node.left == null && node.right == null && node.val == target) {
            result.add(new ArrayList<>(path));
        }

        /*递归遍历左子树和右子树*/
        dfs(node.left, target - node.val, path, result);
        dfs(node.right, target - node.val, path, result);

        /*回溯，将当前节点从路径中删除*/
        path.remove(path.size() - 1);
    }

    /**
     * 计算二叉树中从根节点到叶子节点路径上所有节点值之和等于目标值的路径数量
     *
     * @param root 二叉树的根节点
     * @param sum  目标值
     * @return 符合条件的路径数量
     */
    public int pathsOfSum(TreeNode root, int sum) {
        /*如果当前节点为空，返回 0*/
        if (root == null) {
            return 0;
        }
        /*计算当前节点左子树中符合条件的路径数量*/
        int left = pathsOfSum(root.left, sum - root.val);
        /*计算当前节点右子树中符合条件的路径数量*/
        int right = pathsOfSum(root.right, sum - root.val);
        /*如果当前节点的值等于目标值，说明当前节点可以作为一条符合条件的路径的起点，此时返回 1，否则返回 0*/
        return (sum == root.val ? 1 : 0) + left + right;
    }

    public static void main(String[] args) {
        /* 创建一个二叉树作为示例*/
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        int targetSum = 22;
        int pathCount = new PathSumOfTree().pathsOfSum(root, targetSum);
        /*Path count: 2*/
        System.out.println("Path count: " + pathCount);

        List<List<Integer>> paths = new PathSumOfTree().binaryTreePathSum(root, targetSum);

        /*输出结果*/
        for (List<Integer> path : paths) {
            System.out.println(path);
        }

    }
}
