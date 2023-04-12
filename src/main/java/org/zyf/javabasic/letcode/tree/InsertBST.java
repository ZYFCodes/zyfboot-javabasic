package org.zyf.javabasic.letcode.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @author yanfengzhang
 * @description 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。
 * 返回插入后二叉搜索树的根节点。输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
 * @date 2023/4/11  23:31
 */
public class InsertBST {

    /**
     * 从根节点开始，判断插入值与当前节点值的大小关系。
     * 如果插入值小于当前节点值，并且当前节点左子树为空，则将插入值作为当前节点的左子节点。
     * 如果插入值大于当前节点值，并且当前节点右子树为空，则将插入值作为当前节点的右子节点。
     * 如果插入值小于当前节点值，并且当前节点左子树非空，则以当前节点的左子节点为根节点，递归执行步骤1。
     * 如果插入值大于当前节点值，并且当前节点右子树非空，则以当前节点的右子节点为根节点，递归执行步骤1。
     * 最终返回根节点即可。
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        /*如果根节点为空，则创建一个新节点作为根节点并返回*/
        if (root == null) {
            return new TreeNode(val);
        }
        /*如果插入值小于当前节点值，则插入到左子树中*/
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        }
        /*如果插入值大于等于当前节点值，则插入到右子树中*/
        else {
            root.right = insertIntoBST(root.right, val);
        }
        /*返回插入后的根节点*/
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        TreeNode newRoot = new InsertBST().insertIntoBST(root, 5);

        /*4*/
        System.out.println(newRoot.val);
        /*7*/
        System.out.println(newRoot.right.val);
        /*5*/
        System.out.println(newRoot.right.left.val);
    }


}
