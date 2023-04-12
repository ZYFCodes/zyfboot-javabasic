package org.zyf.javabasic.letcode.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @author yanfengzhang
 * @description 给定一个二叉搜索树（Binary Search Tree），
 * 删除其中的一个节点，并保证删除后的树仍然是一棵二叉搜索树。
 * @date 2023/4/11  23:36
 */
public class DeleteBST {

    /**
     * 二叉搜索树是一棵有序的树，对于一个节点，其左子树中的节点都比它小，右子树中的节点都比它大。因此，删除操作需要考虑三种情况：
     * <p>
     * 要删除的节点没有子节点，直接删除即可；
     * 要删除的节点只有一个子节点，将其子节点上移，然后删除该节点；
     * 要删除的节点有两个子节点，需要找到其右子树上的最小节点，将该节点替换到要删除的节点上，然后删除该节点。
     * 为了方便操作，我们可以写一个递归函数 removeNode(node, key) 来删除节点。其中，node 表示当前节点，key 表示要删除的节点值。
     * <p>
     * 对于当前节点 node，如果它等于 key，则需要删除它。此时，我们需要根据节点子树的情况进行分类讨论：
     * <p>
     * 如果 node 没有左子节点和右子节点，直接返回 null 即可；
     * 如果 node 没有左子节点，则直接返回其右子节点，用其右子节点替换 node 即可；
     * 如果 node 没有右子节点，则直接返回其左子节点，用其左子节点替换 node 即可；
     * 如果 node 同时拥有左右子节点，则需要找到其右子树上的最小节点 minNode，用 minNode 替换 node，然后将 minNode 删除即可。
     * 时间复杂度：删除操作的时间复杂度为 O(h)，其中 h 是二叉搜索树的高度。
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        /*1. 如果当前节点为空，则返回空节点*/
        if (root == null) {
            return null;
        }
        /*2. 如果要删除的节点值小于当前节点值，则在左子树中进行删除操作*/
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        }
        /*3. 如果要删除的节点值大于当前节点值，则在右子树中进行删除操作*/
        else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }
        /*4. 如果要删除的节点就是当前节点*/
        else {
            /*4.1 如果当前节点没有左右子树，则直接删除该节点*/
            if (root.left == null && root.right == null) {
                root = null;
                /*4.2 如果当前节点有右子树，则将右子树中最小的节点值赋值给当前节点，并在右子树中删除该节点*/
            } else if (root.right != null) {
                root.val = findMin(root.right);
                root.right = deleteNode(root.right, root.val);
            }
            /*4.3 如果当前节点只有左子树，则将左子树中最大的节点值赋值给当前节点，并在左子树中删除该节点*/
            else {
                root.val = findMax(root.left);
                root.left = deleteNode(root.left, root.val);
            }
        }
        /*5. 返回删除操作后的二叉搜索树根节点*/
        return root;
    }

    /*找到二叉搜索树中最小的节点值*/
    private int findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }

    /*找到二叉搜索树中最大的节点值*/
    private int findMax(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node.val;
    }

    public static void main(String[] args) {
        /*构造二叉搜索树*/
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        /*删除值为3的节点*/
        TreeNode newRoot = new DeleteBST().deleteNode(root, 3);
    }

}
