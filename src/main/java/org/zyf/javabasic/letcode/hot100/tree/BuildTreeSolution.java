package org.zyf.javabasic.letcode.hot100.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 从前序与中序遍历序列构造二叉树（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 12:04
 **/
public class BuildTreeSolution {
    private int preIndex = 0;  // 用于记录当前先序遍历的索引
    private Map<Integer, Integer> inOrderMap = new HashMap<>();  // 记录中序遍历中节点的索引位置

    // 主函数：根据先序遍历和中序遍历构建二叉树
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 记录中序遍历中每个节点的位置
        for (int i = 0; i < inorder.length; i++) {
            inOrderMap.put(inorder[i], i);
        }
        // 调用递归函数构建树
        return buildTreeHelper(preorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    // 递归函数：构建树的节点
    private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd, int inStart, int inEnd) {
        // 如果子树为空，返回null
        if (preStart > preEnd || inStart > inEnd) return null;

        // 根节点的值为先序遍历中的第一个元素
        int rootValue = preorder[preStart];
        TreeNode root = new TreeNode(rootValue);

        // 找到根节点在中序遍历中的索引
        int inRootIndex = inOrderMap.get(rootValue);
        int leftTreeSize = inRootIndex - inStart;  // 左子树的大小

        // 递归构建左子树
        root.left = buildTreeHelper(preorder, preStart + 1, preStart + leftTreeSize, inStart, inRootIndex - 1);
        // 递归构建右子树
        root.right = buildTreeHelper(preorder, preStart + leftTreeSize + 1, preEnd, inRootIndex + 1, inEnd);

        return root;
    }

    // 主函数，用于测试
    public static void main(String[] args) {
        BuildTreeSolution solution = new BuildTreeSolution();

        // 示例1
        int[] preorder1 = {3, 9, 20, 15, 7};
        int[] inorder1 = {9, 3, 15, 20, 7};
        TreeNode root1 = solution.buildTree(preorder1, inorder1);
        printInOrder(root1);  // 打印中序遍历检查结果
        System.out.println();
        printPreOrder(root1); // 打印先序遍历检查结果

        // 示例2
        int[] preorder2 = {-1};
        int[] inorder2 = {-1};
        TreeNode root2 = solution.buildTree(preorder2, inorder2);
        printInOrder(root2);  // 打印中序遍历检查结果
        System.out.println();
        printPreOrder(root2); // 打印先序遍历检查结果
    }

    // 打印树的中序遍历
    private static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    // 打印树的先序遍历
    private static void printPreOrder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        printPreOrder(root.left);
        printPreOrder(root.right);
    }
}
