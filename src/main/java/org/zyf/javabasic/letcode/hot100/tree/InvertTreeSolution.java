package org.zyf.javabasic.letcode.hot100.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @program: zyfboot-javabasic
 * @description: 翻转二叉树（简单）
 * @author: zhangyanfeng
 * @create: 2024-08-22 11:00
 **/
public class InvertTreeSolution {
    // 翻转二叉树的递归方法
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 交换左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 递归翻转左右子树
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    // 打印树的中序遍历（用于验证）
    public void printInOrder(TreeNode root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.print(root.val + " ");
            printInOrder(root.right);
        }
    }

    // 测试主函数
    public static void main(String[] args) {
        // 构造测试用例
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(9);

        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);

        TreeNode root3 = null;

        // 创建 Solution 实例并进行测试
        InvertTreeSolution solution = new InvertTreeSolution();

        // 翻转并打印结果
        TreeNode invertedRoot1 = solution.invertTree(root1);
        TreeNode invertedRoot2 = solution.invertTree(root2);
        TreeNode invertedRoot3 = solution.invertTree(root3);

        System.out.print("Inverted Tree 1 (InOrder): ");
        solution.printInOrder(invertedRoot1); // 输出应为 9 7 6 4 3 2 1
        System.out.println();

        System.out.print("Inverted Tree 2 (InOrder): ");
        solution.printInOrder(invertedRoot2); // 输出应为 3 2 1
        System.out.println();

        System.out.print("Inverted Tree 3 (InOrder): ");
        solution.printInOrder(invertedRoot3); // 输出应为空
        System.out.println();
    }
}
