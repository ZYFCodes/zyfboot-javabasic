package org.zyf.javabasic.letcode.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @author yanfengzhang
 * @description 给定一棵二叉树和两个节点，求它们的最近公共祖先。
 * 最近公共祖先是指在二叉树中同时拥有给定节点为其子节点的最近的公共祖先节点。
 * @date 2023/4/11  23:17
 */
public class CommonAncestor {

    /**
     * 根据最近公共祖先的定义，若节点 p、q 分别在左右子树中，则其最近公共祖先为根节点；
     * 若两节点均在左子树中，则继续在左子树中寻找最近公共祖先；
     * 若两节点均在右子树中，则继续在右子树中寻找最近公共祖先。
     * 因此可以使用递归的方式来实现。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /*如果树为空，返回 null*/
        if (root == null) {
            return null;
        }
        /*如果节点 p 或节点 q 与当前节点重合，则说明当前节点是 p 和 q 的最近公共祖先，返回当前节点*/
        if (root == p || root == q) {
            return root;
        }
        /*在左子树中寻找 p 和 q 的最近公共祖先，得到变量 left*/
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        /*在右子树中寻找 p 和 q 的最近公共祖先，得到变量 right*/
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        /*如果 left 和 right 都为空，则说明 p 和 q 不在树中，返回 null*/
        if (left == null && right == null) {
            return null;
        }
        /*如果 left 和 right 都不为空，则说明 p 和 q 分别在当前节点的左右子树中，当前节点即为最近公共祖先，返回当前节点*/
        if (left != null && right != null) {
            return root;
        }
        /*如果 left 为空，则说明 p 和 q 均不在当前节点的左子树中，返回 right*/
        if (left == null) {
            return right;
        }
        /*如果 right 为空，则说明 p 和 q 均不在当前节点的右子树中，返回 left*/
        return left;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode p = root.left;
        TreeNode q = root.right;
        TreeNode lca = new CommonAncestor().lowestCommonAncestor(root, p, q);
        System.out.println("p=" + p.val + ", q=" + q.val + ", LCA=" + lca.val);

        p = root.left;
        q = root.left.right.right;
        lca = new CommonAncestor().lowestCommonAncestor(root, p, q);
        System.out.println("p=" + p.val + ", q=" + q.val + ", LCA=" + lca.val);
    }
}
