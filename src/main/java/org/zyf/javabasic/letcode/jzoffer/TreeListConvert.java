package org.zyf.javabasic.letcode.jzoffer;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @author yanfengzhang
 * @description 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * @date 2023/6/12  22:25
 */
public class TreeListConvert {
    // 全局变量，用于保存当前节点的前一个节点
    private TreeNode pre;

    /**
     * 本题可以使用中序遍历的思想进行求解。具体步骤如下：
     * 1.	定义一个全局变量 pre，用于保存当前节点的前一个节点。
     * 2.	定义一个递归函数 convertHelper，接收一个当前节点作为参数。
     * 3.	在 convertHelper 中，首先递归处理当前节点的左子树，即调用 convertHelper(node.left)。
     * 4.	在左子树递归完成后，将当前节点与前一个节点 pre 建立双向连接关系，即 node.left = pre，if pre != null then pre.right = node。
     * 5.	更新 pre 为当前节点 node。
     * 6.	递归处理当前节点的右子树，即调用 convertHelper(node.right)。
     * 7.	最后返回双向链表的头节点，即最左侧的节点。
     */
    public TreeNode convert(TreeNode root) {
        if (root == null) {
            return null;
        }

        // 递归处理二叉搜索树
        convertHelper(root);

        // 返回双向链表的头节点，即最左侧的节点
        TreeNode head = root;
        while (head.left != null) {
            head = head.left;
        }
        return head;
    }

    private void convertHelper(TreeNode node) {
        if (node == null) {
            return;
        }

        // 递归处理左子树
        convertHelper(node.left);

        // 当前节点与前一个节点建立双向连接
        node.left = pre;
        if (pre != null) {
            pre.right = node;
        }
        pre = node;

        // 递归处理右子树
        convertHelper(node.right);
    }

    public static void main(String[] args) {
        TreeListConvert solution = new TreeListConvert();

        // 构建二叉搜索树
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        TreeNode result = solution.convert(root);
        printDoubleLinkedList(result);
    }

    // 打印双向链表
    private static void printDoubleLinkedList(TreeNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.right;
        }
    }
}
