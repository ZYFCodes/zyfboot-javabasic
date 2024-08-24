package org.zyf.javabasic.letcode.hot100.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @program: zyfboot-javabasic
 * @description: 二叉树展开为链表（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 11:59
 **/
public class FlattenSolution {
    // 主函数：将二叉树展开为单链表
    public void flatten(TreeNode root) {
        if (root == null) return;  // 如果树为空，直接返回

        // 先序遍历并展开右子树
        flattenTree(root);
    }

    // 辅助函数：先序遍历树并将其展开为链表
    private TreeNode flattenTree(TreeNode node) {
        if (node == null) return null;  // 如果节点为空，返回空

        // 递归展开左子树
        TreeNode left = flattenTree(node.left);
        // 递归展开右子树
        TreeNode right = flattenTree(node.right);

        // 如果左子树不为空，将其插入到右子树前面
        if (left != null) {
            // 将左子树的右子树连接到右子树上
            TreeNode temp = left;
            while (temp.right != null) {
                temp = temp.right;
            }
            temp.right = right;  // 将右子树接到左子树的末尾

            // 将左子树作为右子树
            node.right = left;
            node.left = null;  // 左子树应为空
        }

        // 返回当前节点
        return node;
    }

    // 主函数，用于测试
    public static void main(String[] args) {
        // 构造测试用例 [1,2,5,3,4,null,6]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        // 调用函数展开树
        FlattenSolution solution = new FlattenSolution();
        solution.flatten(root);

        // 打印展开后的链表
        TreeNode current = root;
        while (current != null) {
            System.out.print(current.val);
            if (current.right != null) {
                System.out.print(" -> ");
            }
            current = current.right;
        }
    }
}
