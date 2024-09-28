package org.zyf.javabasic.letcode.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @program: zyfboot-javabasic
 * @description: 测试
 * @author: zhangyanfeng
 * @create: 2024-09-02 16:29
 **/
public class TreeToList {

    //前去节点
    private TreeNode prev = null;
    //双向链表头节点
    private TreeNode head = null;

    public TreeNode coverToDoubleList(TreeNode root) {
        if (root == null) {
            return null;
        }

        inorderTraversal(root);
        return head;
    }

    private void inorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        //左子树
        inorderTraversal(node.left);

        if (prev == null) {
            head = node;
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;

        //右子树
        inorderTraversal(node.right);
    }

    public void printDoubleListForward(TreeNode head) {
        TreeNode current = head;
        while (current != null) {
            System.out.println(current.val + "");
            current = current.right;
        }
        System.out.println();
    }

    public void printDoubleListBackward(TreeNode tail) {
        TreeNode current = tail;
        while (current != null) {
            System.out.println(current.val + "");
            current = current.left;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        //转双向
        TreeToList treeToList = new TreeToList();
        TreeNode head = treeToList.coverToDoubleList(root);

        //前向遍历
        System.out.println("Forward:");
        treeToList.printDoubleListForward(head);

        //找到链表尾部
        TreeNode tail = head;
        while (tail.right != null) {
            tail = tail.right;
        }

        System.out.println("Backward:");
        treeToList.printDoubleListBackward(tail);
    }


}
