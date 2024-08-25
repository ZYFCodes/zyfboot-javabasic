package org.zyf.javabasic.letcode.jd150.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @program: zyfboot-javabasic
 * @description: 相同的树
 * @author: zhangyanfeng
 * @create: 2024-08-25 13:40
 **/
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 如果两个节点都为 null，则两棵树相同
        if (p == null && q == null) {
            return true;
        }
        // 如果其中一个节点为 null 或者两个节点的值不同，则两棵树不相同
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        // 递归判断左右子树是否相同
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        SameTree sol = new SameTree();

        // 示例 1
        TreeNode p1 = new TreeNode(1);
        p1.left = new TreeNode(2);
        p1.right = new TreeNode(3);
        TreeNode q1 = new TreeNode(1);
        q1.left = new TreeNode(2);
        q1.right = new TreeNode(3);

        // 示例 2
        TreeNode p2 = new TreeNode(1);
        p2.left = new TreeNode(2);
        TreeNode q2 = new TreeNode(1);
        q2.right = new TreeNode(2);

        // 示例 3
        TreeNode p3 = new TreeNode(1);
        p3.left = new TreeNode(2);
        p3.right = new TreeNode(1);
        TreeNode q3 = new TreeNode(1);
        q3.left = new TreeNode(1);
        q3.right = new TreeNode(2);

        System.out.println(sol.isSameTree(p1, q1)); // 输出: true
        System.out.println(sol.isSameTree(p2, q2)); // 输出: false
        System.out.println(sol.isSameTree(p3, q3)); // 输出: false
    }
}
