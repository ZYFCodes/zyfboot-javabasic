package org.zyf.javabasic.letcode.jzoffer;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @author yanfengzhang
 * @description 给定一棵二叉搜索树，请找出其中第k大的节点。
 * @date 2023/6/9  22:58
 */
public class kthLargestTree {
    // 计数器，记录当前遍历到的节点序号
    private int count = 0;
    // 存储第k大的节点的值
    private int result = 0;

    /**
     * 二叉搜索树的特点是，左子树上的节点值都小于根节点值，右子树上的节点值都大于根节点值。因此，中序遍历二叉搜索树可以得到一个递增的节点值序列。
     * 根据这个特点，可以使用中序遍历的思想来解决该问题。具体步骤如下：
     * 1.	对二叉搜索树进行中序遍历，即先遍历左子树，再访问根节点，最后遍历右子树。
     * 2.	在中序遍历的过程中，使用一个计数器记录当前遍历到的节点的序号，当计数器等于k时，表示找到了第k大的节点。
     * 3.	在遍历过程中，如果找到了第k大的节点，则直接返回该节点的值。
     */
    public int kthLargest(TreeNode root, int k) {
        // 从右子树开始进行中序遍历，即先遍历右子树，再访问根节点，最后遍历左子树
        inorderTraversal(root, k);
        return result;
    }

    private void inorderTraversal(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        // 遍历右子树
        inorderTraversal(root.right, k);

        // 计数器加1
        count++;
        // 如果计数器等于k，表示找到了第k大的节点
        if (count == k) {
            result = root.val;
            return;
        }

        // 遍历左子树
        inorderTraversal(root.left, k);
    }

    public static void main(String[] args) {
        kthLargestTree solution = new kthLargestTree();

        // 构建二叉搜索树
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);

        int k = 3;

        int kthLargest = solution.kthLargest(root, k);
        System.out.println(kthLargest);
    }
}
