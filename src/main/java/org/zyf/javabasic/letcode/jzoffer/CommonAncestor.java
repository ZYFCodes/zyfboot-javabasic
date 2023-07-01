package org.zyf.javabasic.letcode.jzoffer;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @author yanfengzhang
 * @description 给定一个二叉搜索树 (BST)，找到该树中两个指定节点的最近公共祖先（LCA）。
 * 一个节点也可以是它自己的祖先。
 * @date 2023/6/9  22:52
 */
public class CommonAncestor {
    /**
     * 由于二叉搜索树具有左子树节点值小于根节点值，右子树节点值大于根节点值的特点，可以利用这个特点进行求解。
     * 1.	从根节点开始遍历二叉搜索树。
     * 2.	如果当前节点的值大于 p 和 q 的值，说明 p 和 q 分别位于当前节点的左子树中，因此需要向左子树移动。
     * 3.	如果当前节点的值小于 p 和 q 的值，说明 p 和 q 分别位于当前节点的右子树中，因此需要向右子树移动。
     * 4.	如果当前节点的值不满足上述两种情况，则说明当前节点就是最近公共祖先节点。
     * 可以使用迭代或递归的方式实现该算法。
     * <p>
     * 以下是使用迭代的解题思路：
     * 1.	初始化当前节点为根节点。
     * 2.	进入循环，直到找到最近公共祖先为止。
     * 3.	如果当前节点的值大于 p 和 q 的值，则将当前节点移动到左子节点。
     * 4.	如果当前节点的值小于 p 和 q 的值，则将当前节点移动到右子节点。
     * 5.	否则，当前节点即为最近公共祖先，退出循环。
     * 6.	返回最近公共祖先节点。
     * 请注意，题目中假设给定的两个节点一定存在于该二叉搜索树中。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 遍历二叉搜索树，寻找最近公共祖先
        while (root != null) {
            // 当前节点的值大于 p 和 q 的值，向左子树移动
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            }
            // 当前节点的值小于 p 和 q 的值，向右子树移动
            else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            }
            // 当前节点的值不满足上述两种情况，即为最近公共祖先节点
            else {
                break;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        CommonAncestor solution = new CommonAncestor();

        // 构建二叉搜索树
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        // 节点2
        TreeNode p = root.left;
        // 节点8
        TreeNode q = root.right;

        TreeNode ancestor = solution.lowestCommonAncestor(root, p, q);
        // 打印最近公共祖先节点的值
        System.out.println(ancestor.val);
    }

}
