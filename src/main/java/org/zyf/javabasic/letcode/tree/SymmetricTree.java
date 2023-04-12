package org.zyf.javabasic.letcode.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @author yanfengzhang
 * @description 给定一棵二叉树，判断它是否是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 * @date 2023/4/11  23:13
 */
public class SymmetricTree {

    /**
     * 判断一棵二叉树是否为对称二叉树的最优解法是递归。可以通过比较二叉树的左右子树是否对称来判断整棵二叉树是否对称。具体实现步骤如下：
     * <p>
     * 定义递归函数 isSymmetric(left, right)，表示判断以 left 和 right 为根节点的两棵二叉树是否对称。
     * 如果 left 和 right 都为空，则返回 true。
     * 如果 left 和 right 中有且只有一个为空，则返回 false。
     * 如果 left 和 right 的根节点的值不相等，则返回 false。
     * 递归判断 left.left 和 right.right 是否对称，递归判断 left.right 和 right.left 是否对称，只有两个都对称时，整棵树才对称。
     * 在主函数中，调用递归函数 isSymmetric(root, root) 判断整棵树是否对称。
     */
    public boolean isSymmetric(TreeNode root) {
        /*如果根节点为空，返回true*/
        if (root == null) {
            return true;
        }
        /*检查左右子树是否对称*/
        return checkSymmetric(root.left, root.right);
    }

    /*检查左右子树是否对称*/
    private boolean checkSymmetric(TreeNode left, TreeNode right) {
        /*如果左右子树都为空，返回true*/
        if (left == null && right == null) {
            return true;
        }
        /*如果左右子树有一个为空，返回false*/
        if (left == null || right == null) {
            return false;
        }
        /*如果左右子树的节点值不相等，返回false*/
        if (left.val != right.val) {
            return false;
        }
        /*递归检查左子树的左子树是否与右子树的右子树对称*/
        /*以及左子树的右子树是否与右子树的左子树对称*/
        return checkSymmetric(left.left, right.right) && checkSymmetric(left.right, right.left);
    }

    public static void main(String[] args) {
        /*创建二叉树*/
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        /*判断是否为对称二叉树*/
        boolean result = new SymmetricTree().isSymmetric(root);

        /*输出结果*/
        if (result) {
            System.out.println("是对称二叉树");
        } else {
            System.out.println("不是对称二叉树");
        }
    }
}
