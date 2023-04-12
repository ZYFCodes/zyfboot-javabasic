package org.zyf.javabasic.letcode.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 给定一棵二叉树的前序遍历和中序遍历，需要构造该二叉树。
 * 前序遍历的顺序为：根节点、左子树、右子树；
 * 中序遍历的顺序为：左子树、根节点、右子树。
 * @date 2023/4/13  00:07
 */
public class BuildTreeByTraver {
    /**
     * 通过前序遍历和中序遍历构造二叉树
     *
     * @param preorder 前序遍历序列
     * @param inorder  中序遍历序列
     * @return 构造完成的二叉树根节点
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        /*构造中序遍历序列中节点值和对应索引的映射表*/
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, inorderIndexMap, 0, inorder.length - 1);
    }

    /**
     * 在指定的前序遍历序列范围和中序遍历序列范围内构造二叉树
     *
     * @param preorder        前序遍历序列
     * @param preStart        前序遍历序列的起始索引
     * @param preEnd          前序遍历序列的终止索引
     * @param inorderIndexMap 中序遍历序列的节点值和索引的映射表
     * @param inStart         中序遍历序列的起始索引
     * @param inEnd           中序遍历序列的终止索引
     * @return 构造完成的二叉树根节点
     */
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, Map<Integer, Integer> inorderIndexMap,
                               int inStart, int inEnd) {
        /*如果前序遍历序列的起始索引大于终止索引，则构造完成*/
        if (preStart > preEnd) {
            return null;
        }
        /*前序遍历序列的第一个节点是当前子树的根节点*/
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        /*如果前序遍历序列中仅有一个节点，直接返回当前节点作为根节点*/
        if (preStart == preEnd) {
            return root;
        }
        /*在中序遍历序列中找到根节点的索引*/
        int rootIndexInorder = inorderIndexMap.get(rootVal);
        /*根据中序遍历中根节点的索引，将左右子树分别递归构造*/
        int leftSubtreeSize = rootIndexInorder - inStart;
        int rightSubtreeSize = inEnd - rootIndexInorder;
        TreeNode leftSubtree = buildTree(preorder, preStart + 1, preStart + leftSubtreeSize,
                inorderIndexMap, inStart, rootIndexInorder - 1);
        TreeNode rightSubtree = buildTree(preorder, preStart + leftSubtreeSize + 1, preEnd,
                inorderIndexMap, rootIndexInorder + 1, inEnd);
        /*将构造好的左右子树挂到当前根节点上*/
        root.left = leftSubtree;
        root.right = rightSubtree;
        return root;
    }


    public static void main(String[] args) {
        int[] preorder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inorder = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode root = new BuildTreeByTraver().buildTree(preorder, inorder);
    }

}
