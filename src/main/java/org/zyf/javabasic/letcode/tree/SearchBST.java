package org.zyf.javabasic.letcode.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @author yanfengzhang
 * @description 给定一个二叉搜索树（Binary Search Tree, BST）和一个值。
 * 你需要在BST中找到节点值等于给定值的节点。
 * 返回以该节点作为根的子树。如果节点不存在，则返回NULL。
 * @date 2023/4/12  23:57
 */
public class SearchBST {

    /**
     * 通过递归的方式从根节点开始遍历二叉搜索树，
     * 如果当前节点为 null 或者值等于目标值，则返回当前节点。
     * 否则，如果当前节点的值小于目标值，则在其右子树中继续查找；
     * 如果当前节点的值大于目标值，则在其左子树中继续查找。
     * 这样，如果二叉搜索树中存在目标值，就可以找到它并返回对应的节点，如果不存在，则返回 null。
     */
    public boolean searchBST1(TreeNode root, int val) {
        if (root == null) {
            return false;
        }
        if (root.val == val) {
            return true;
        }
        if (root.val > val) {
            return searchBST1(root.left, val);
        }
        return searchBST1(root.right, val);
    }

    /**
     * 通过迭代的方式从根节点开始遍历二叉搜索树，
     * 如果当前节点为 null 或者值等于目标值，则返回当前节点。
     * 否则，如果当前节点的值小于目标值，则在其右子树中继续查找；
     * 如果当前节点的值大于目标值，则在其左子树中继续查找。
     * 这样，如果二叉搜索树中存在目标值，就可以找到它并返回对应的节点，如果不存在，则返回 null。
     */
    public boolean searchBST2(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val) {
                return true;
            } else if (root.val > val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        /*构建一棵二叉搜索树*/
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);

        /*测试 searchBST1 方法*/
        /*true*/
        System.out.println(new SearchBST().searchBST1(root, 6));
        /*false*/
        System.out.println(new SearchBST().searchBST1(root, 9));

        /*测试 searchBST2 方法*/
        /*true*/
        System.out.println(new SearchBST().searchBST2(root, 6));
        /*false*/
        System.out.println(new SearchBST().searchBST2(root, 9));
    }
}
