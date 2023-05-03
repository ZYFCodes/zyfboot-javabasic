package org.zyf.javabasic.letcode.tree;


import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @author yanfengzhang
 * @description 给定一个二叉树和一个目标和，
 * 判断该树中是否存在根节点到叶子节点的路径，
 * 这条路径上所有节点值相加等于给定的目标和。
 * @date 2023/4/11  23:27
 */
public class PathSumOfTree {

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
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        int targetSum = 8;
        int pathCount = new PathSumOfTree().pathsOfSum(root, targetSum);
        /*Path count: 3*/
        System.out.println("Path count: " + pathCount);
    }
}
