package org.zyf.javabasic.letcode.jzoffer;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @author yanfengzhang
 * @description 给定一个二叉树和一个目标值target，
 * 判断该二叉树中是否存在从根节点到叶子节点的路径，使得路径上的节点值之和等于target。
 * @date 2023/6/10  23:12
 */
public class verifyPathSumTree {
    /**
     * 可以使用递归的方式来解决该问题。从根节点开始，递归地遍历左子树和右子树，并更新目标值为target减去当前节点的值。
     * 如果当前节点是叶子节点且目标值等于当前节点的值，说明存在路径满足要求。
     * 否则，继续递归遍历左子树和右子树。最终返回左子树或右子树中是否存在满足要求的路径。
     * 具体步骤如下：
     * 1.	如果根节点为空，直接返回false。
     * 2.	如果根节点是叶子节点且目标值等于当前节点的值，返回true。
     * 3.	递归遍历左子树和右子树，更新目标值为target减去当前节点的值，并判断左子树或右子树中是否存在满足要求的路径，如果存在，则返回true。
     * 4.	如果左子树和右子树中都不存在满足要求的路径，返回false。
     * <p>
     * 这种递归的解法可以利用二叉树的前序遍历实现。
     * 时间复杂度分析：假设二叉树的节点数为N，每个节点都需要遍历一次，因此时间复杂度为O(N)。
     * 空间复杂度分析：递归调用栈的深度最大为二叉树的高度，
     * 如果二叉树是平衡二叉树，则空间复杂度为O(logN)；如果二叉树是非平衡二叉树，则空间复杂度为O(N)。
     */
    public boolean hasPathSum(TreeNode root, int target) {
        // 根节点为空，返回false
        if (root == null) {
            return false;
        }

        // 当前节点是叶子节点且目标值等于当前节点的值，返回true
        if (root.left == null && root.right == null && target == root.val) {
            return true;
        }

        // 递归遍历左子树和右子树，更新目标值为target减去当前节点的值
        boolean leftResult = hasPathSum(root.left, target - root.val);
        boolean rightResult = hasPathSum(root.right, target - root.val);

        // 如果左子树或右子树存在满足要求的路径，返回true
        if (leftResult || rightResult) {
            return true;
        }

        // 左子树和右子树都不存在满足要求的路径，返回false
        return false;
    }

    public static void main(String[] args) {
        verifyPathSumTree solution = new verifyPathSumTree();

        // 构建二叉树
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        int target = 22;
        boolean result = solution.hasPathSum(root, target);
        System.out.println(result);
    }

}
