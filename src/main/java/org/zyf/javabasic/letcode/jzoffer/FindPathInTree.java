package org.zyf.javabasic.letcode.jzoffer;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.ArrayList;

/**
 * @author yanfengzhang
 * @description 输入一颗二叉树的根结点和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * @date 2023/6/11  22:15
 */
public class FindPathInTree {
    /**
     * 使用回溯法（Backtracking）解决问题。
     * 从根节点开始遍历树，每遍历到一个节点，将当前节点加入路径中，并更新目标值。
     * 然后分别递归遍历当前节点的左子树和右子树。
     * 当遍历到叶子节点时，判断当前路径上节点值之和是否等于目标值，
     * 如果是，则将当前路径加入结果集中。
     * 最后将当前节点从路径中移除，继续遍历其他路径。
     */
    public ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();

        findPathHelper(root, target, path, result);

        return result;
    }

    private void findPathHelper(TreeNode root, int target,
                                ArrayList<Integer> path,
                                ArrayList<ArrayList<Integer>> result) {
        // 当前节点为空，直接返回
        if (root == null) {
            return;
        }

        // 将当前节点加入路径中
        path.add(root.val);

        // 当前节点是叶子节点且目标值等于当前节点的值
        if (root.left == null && root.right == null && target == root.val) {
            // 将当前路径加入结果集
            result.add(new ArrayList<>(path));
        }

        // 递归遍历左子树和右子树，更新目标值为target减去当前节点的值
        findPathHelper(root.left, target - root.val, path, result);
        findPathHelper(root.right, target - root.val, path, result);

        // 当前节点遍历完成后，将当前节点从路径中移除，回溯到上一层节点
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        FindPathInTree solution = new FindPathInTree();

        // 构建二叉树
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);

        int target = 22;
        ArrayList<ArrayList<Integer>> result = solution.findPath(root, target);
        System.out.println(result);
    }
}
