package org.zyf.javabasic.letcode.jzoffer;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @author yanfengzhang
 * @description 输入两棵二叉树A和B，判断B是不是A的子结构。二叉树节点的定义如下：
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *
 *     TreeNode(int val) {
 *         this.val = val;
 *     }
 * }
 * @date 2023/6/17 00:06
 */
public class SubStructure {
    /**
     * 要判断B是否是A的子结构，可以分为两步进行：
     * 	1.	在树A中找到与树B根节点值相同的节点，作为起始点进行匹配。
     * 	2.	在匹配的起始点处，判断树A中以该节点为根节点的子树是否包含树B，即判断两个子树是否结构相同。
     * 具体步骤如下：
     * 	1.	遍历树A，寻找与树B根节点值相同的节点。
     * 	•	如果找到了与树B根节点值相同的节点，则进入第2步。
     * 	•	如果遍历完树A仍未找到相同的节点，则返回false。
     * 	2.	以找到的相同节点为起始点，判断树A中以该节点为根节点的子树是否包含树B。
     * 	•	如果树B为null，表示树B已经匹配完毕，返回true。
     * 	•	如果树A为null，表示树A已经遍历完毕，而树B还未匹配完毕，返回false。
     * 	•	如果树A的节点值与树B的节点值不相同，返回false。
     * 	•	分别判断树A的左子树和右子树是否包含树B，如果两者有一个为true，则返回true；否则返回false。
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }

        // 在树A中找到与树B根节点值相同的节点
        if (A.val == B.val && isMatch(A, B)) {
            return true;
        }

        // 递归判断树A的左子树和右子树是否包含树B
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean isMatch(TreeNode A, TreeNode B) {
        // 如果树B已经匹配完毕，返回true
        if (B == null) {
            return true;
        }

        // 如果树A遍历完毕，而树B还未匹配完毕，返回false
        if (A == null) {
            return false;
        }

        // 如果节点值不相同，返回false
        if (A.val != B.val) {
            return false;
        }

        // 递归判断树A的左子树和右子树是否包含树B
        return isMatch(A.left, B.left) && isMatch(A.right, B.right);
    }

    public static void main(String[] args) {
        SubStructure solution = new SubStructure();

        // 构建二叉树A
        TreeNode A = new TreeNode(3);
        A.left = new TreeNode(4);
        A.right = new TreeNode(5);
        A.left.left = new TreeNode(1);
        A.left.right = new TreeNode(2);

        // 构建二叉树B
        TreeNode B = new TreeNode(4);
        B.left = new TreeNode(1);

        boolean result = solution.isSubStructure(A, B);
        // 打印结果
        System.out.println(result);
    }
}
