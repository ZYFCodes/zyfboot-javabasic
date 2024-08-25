package org.zyf.javabasic.letcode.jd150.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @program: zyfboot-javabasic
 * @description: 二叉搜索树的最小绝对差
 * @author: zhangyanfeng
 * @create: 2024-08-25 14:50
 **/
public class MinDiffInBST {
    // 记录上一个节点的值，初始为极小值
    private Integer prev = null;
    // 记录最小差值，初始为最大值
    private int minDiff = Integer.MAX_VALUE;

    public int minDiffInBST(TreeNode root) {
        // 调用中序遍历的辅助函数
        inOrder(root);
        return minDiff;
    }

    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        // 中序遍历左子树
        inOrder(node.left);

        // 处理当前节点
        if (prev != null) {
            // 计算当前节点与上一个节点值的差，并更新最小差值
            minDiff = Math.min(minDiff, node.val - prev);
        }
        // 更新上一个节点值
        prev = node.val;

        // 中序遍历右子树
        inOrder(node.right);
    }
}
