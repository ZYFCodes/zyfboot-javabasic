package org.zyf.javabasic.letcode.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @author yanfengzhang
 * @description 定一棵二叉树，你需要计算它的直径长度。
 * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
 * 这条路径可能穿过也可能不穿过根结点。
 * @date 2023/4/11  23:21
 */
public class DiameterOfTree {
    /*记录二叉树的最长路径*/
    private int maxPath = 0;

    /**
     * 对于每个节点，其最长路径可以表示为其左子树的最大深度加上右子树的最大深度。
     * 因此可以通过递归的方式，计算每个节点的最长路径，取其中的最大值作为整个二叉树的最长路径。
     */
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return maxPath;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        /*计算左子树的最大深度*/
        int left = maxDepth(root.left);
        /*计算右子树的最大深度*/
        int right = maxDepth(root.right);
        /*更新最长路径*/
        maxPath = Math.max(maxPath, left + right);
        /*返回当前节点的最大深度*/
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        int diameter = new DiameterOfTree().diameterOfBinaryTree(root);
        System.out.println("Diameter of the binary tree: " + diameter);
    }

}
