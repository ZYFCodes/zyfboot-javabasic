package org.zyf.javabasic.letcode.jzoffer;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 给定二叉树的前序遍历和中序遍历的结果，请重建该二叉树并返回其根节点。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如，输入前序遍历序列 [3,9,20,15,7] 和中序遍历序列 [9,3,15,20,7]，则重建二叉树并返回其根节点。
 * @date 2023/6/7  00:15
 */
public class BuildTree {
    /**
     * 根据前序遍历的特点，第一个元素是根节点。根据中序遍历的特点，根节点的左边是左子树的中序遍历结果，右边是右子树的中序遍历结果。
     * 1.	定义一个递归函数 buildTree，接收前序遍历序列 preorder、中序遍历序列 inorder、前序遍历序列的起始位置 preStart、
     * 前序遍历序列的结束位置 preEnd、中序遍历序列的起始位置 inStart、中序遍历序列的结束位置 inEnd。
     * 2.	若 preStart > preEnd，说明当前子树为空，返回 null。
     * 3.	创建根节点，值为前序遍历序列的第一个元素 preorder[preStart]。
     * 4.	在中序遍历序列中找到根节点的位置，将中序遍历序列分为左子树和右子树的部分。
     * 5.	根据左子树的长度，确定前序遍历序列中左子树和右子树的范围。
     * 6.	递归构建左子树，调用 buildTree，传入相应的参数。
     * 7.	递归构建右子树，调用 buildTree，传入相应的参数。
     * 8.	返回根节点。
     * 通过递归构建二叉树，可以得到最终的二叉树结构。
     * 解题步骤：
     * 1.	创建 buildTree 函数，传入前序遍历序列 preorder 和中序遍历序列 inorder。
     * 2.	调用 buildTree 函数，传入相应的参数，返回重建后的二叉树的根节点。
     * 这样，就能得到重建的二叉树。
     * 注意：在实现中需要使用额外的数据结构，如数组、哈希表等，以便快速查找元素在中序遍历序列中的位置。
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 创建哈希表，用于快速查找中序遍历序列中元素的位置
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildTree(preorder,
                0,
                preorder.length - 1,
                inorder, 0,
                inorder.length - 1,
                inorderMap);
    }

    private TreeNode buildTree(int[] preorder,
                               int preStart,
                               int preEnd,
                               int[] inorder,
                               int inStart,
                               int inEnd,
                               Map<Integer, Integer> inorderMap) {
        if (preStart > preEnd) {
            return null;
        }

        // 根据前序遍历序列的第一个元素创建根节点
        int rootValue = preorder[preStart];
        TreeNode root = new TreeNode(rootValue);

        // 在中序遍历序列中找到根节点的位置
        int rootIndex = inorderMap.get(rootValue);

        // 计算左子树的长度
        int leftSubtreeSize = rootIndex - inStart;

        // 递归构建左子树
        root.left = buildTree(preorder,
                preStart + 1,
                preStart + leftSubtreeSize,
                inorder,
                inStart,
                rootIndex - 1,
                inorderMap);

        // 递归构建右子树
        root.right = buildTree(preorder,
                preStart + leftSubtreeSize + 1,
                preEnd, inorder,
                rootIndex + 1,
                inEnd,
                inorderMap);

        return root;
    }

    public static void main(String[] args) {
        BuildTree solution = new BuildTree();

        // 构建前序遍历序列和中序遍历序列
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        // 构建二叉树
        TreeNode root = solution.buildTree(preorder, inorder);

        // 验证结果
        // 二叉树构建完成后，可以根据需要进行遍历等操作
        // 这里只验证构建的树结构是否正确
        System.out.println(root.val);
        System.out.println(root.left.val);
        System.out.println(root.right.val);
    }
}
