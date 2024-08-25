package org.zyf.javabasic.letcode.jd150.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @program: zyfboot-javabasic
 * @description: 完全二叉树的节点个数
 * @author: zhangyanfeng
 * @create: 2024-08-25 14:29
 **/
public class CountNodes {
    public int countNodes(TreeNode root) {
        // 如果根节点为空，直接返回0
        if (root == null) {
            return 0;
        }

        // 计算左子树高度
        int leftHeight = getHeight(root.left);
        // 计算右子树高度
        int rightHeight = getHeight(root.right);

        // 如果左子树高度等于右子树高度，说明左子树是满的
        if (leftHeight == rightHeight) {
            // 直接返回左子树节点数 + 右子树递归节点数 + 根节点1
            return (1 << leftHeight) + countNodes(root.right);
        } else {
            // 否则，右子树是满的
            // 直接返回右子树节点数 + 左子树递归节点数 + 根节点1
            return (1 << rightHeight) + countNodes(root.left);
        }
    }

    // 辅助函数，计算树的高度
    private int getHeight(TreeNode root) {
        int height = 0;
        while (root != null) {
            height++;
            root = root.left;
        }
        return height;
    }
}
