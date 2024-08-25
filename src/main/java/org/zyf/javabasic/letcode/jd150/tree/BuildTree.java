package org.zyf.javabasic.letcode.jd150.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 从中序与后序遍历序列构造二叉树
 * @author: zhangyanfeng
 * @create: 2024-08-25 13:50
 **/
public class BuildTree {
    private Map<Integer, Integer> inorderIndexMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        // 创建一个哈希映射以存储中序遍历中每个值的索引
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        // 从后序遍历和中序遍历构建二叉树
        return buildTreeHelper(postorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode buildTreeHelper(int[] postorder, int postStart, int postEnd, int inStart, int inEnd) {
        // 如果索引无效，返回 null
        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }
        // 后序遍历的最后一个节点是当前子树的根节点
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);

        // 在中序遍历中找到根节点的位置
        int inRootIndex = inorderIndexMap.get(rootVal);
        int leftSubtreeSize = inRootIndex - inStart;

        // 递归构建左子树
        root.left = buildTreeHelper(postorder, postStart, postStart + leftSubtreeSize - 1, inStart, inRootIndex - 1);
        // 递归构建右子树
        root.right = buildTreeHelper(postorder, postStart + leftSubtreeSize, postEnd - 1, inRootIndex + 1, inEnd);

        return root;
    }

    public static void main(String[] args) {
        BuildTree sol = new BuildTree();
        int[] inorder1 = {9, 3, 15, 20, 7};
        int[] postorder1 = {9, 15, 7, 20, 3};

        TreeNode root1 = sol.buildTree(inorder1, postorder1);
        printPreorder(root1); // 输出前序遍历结果验证树的构造
    }

    private static void printPreorder(TreeNode node) {
        if (node != null) {
            System.out.print(node.val + " ");
            printPreorder(node.left);
            printPreorder(node.right);
        }
    }
}
