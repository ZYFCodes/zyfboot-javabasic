package org.zyf.javabasic.letcode.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @author yanfengzhang
 * @description 给定一个二叉树，判断它是否是二叉搜索树（Binary Search Tree，简称BST）。
 * @date 2023/6/12  23:02
 */
public class ValidateBST {

    /**
     * 判断一个树是否是二叉搜索树可以使用递归或迭代的方法来解决。二叉搜索树具有以下性质：
     * <p>
     * 1.	对于任意节点，其左子树的所有节点值都小于该节点的值。
     * 2.	对于任意节点，其右子树的所有节点值都大于该节点的值。
     * 3.	对于任意节点，其左子树和右子树都必须是二叉搜索树。
     * <p>
     * 基于以上性质，我们可以采用以下方法判断一个树是否是二叉搜索树：
     * <p>
     * 1.	递归法：
     * •	定义一个递归函数 isValidBST(root, minVal, maxVal)，用于判断以 root 为根节点的子树是否是二叉搜索树。
     * •	如果 root 为空，返回 true。
     * •	如果 root 的值小于等于 minVal 或大于等于 maxVal，则说明不满足二叉搜索树的性质，返回 false。
     * •	递归调用 isValidBST 函数判断左子树和右子树是否是二叉搜索树，其中左子树的最大值应为 root 的值，右子树的最小值应为 root 的值。
     * •	如果左子树和右子树都是二叉搜索树，则说明以 root 为根节点的树也是二叉搜索树，返回 true。
     * 2.	中序遍历法：
     * •	对二叉搜索树进行中序遍历，得到一个递增的节点值序列。
     * •	如果序列是递增的，则说明树是二叉搜索树；否则，树不是二叉搜索树。
     * <p>
     * 通过上述方法，我们可以判断给定的二叉树是否是二叉搜索树。算法的时间复杂度为 O(n)，其中 n 是树中节点的个数。
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer minVal, Integer maxVal) {
        if (root == null) {
            return true;
        }

        if (minVal != null && root.val <= minVal) {
            return false;
        }

        if (maxVal != null && root.val >= maxVal) {
            return false;
        }

        return isValidBST(root.left, minVal, root.val)
                && isValidBST(root.right, root.val, maxVal);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        ValidateBST solution = new ValidateBST();
        boolean isValid = solution.isValidBST(root);
        // 输出 Is Valid BST: true
        System.out.println("Is Valid BST: " + isValid);
    }

}
